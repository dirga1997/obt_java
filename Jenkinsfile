pipeline {
   agent any

   environment {
      NAMESPACE = "ait-standard"
   }

   stages {
     stage('Check Commit') {
       steps {
        script {
          result = sh (script: "git log -1 | grep -E '(feat|build|chore|fix|docs|refactor|perf|style|test)(\\(.+\\))*:'", returnStatus: true)
          if (result != 0) {
            throw new Exception("failed, not meet commit standard!")
          }
        }
       }
     }

     stage('Build') {
       steps {
         sh 'mvn -Dmaven.test.failure.ignore=true install'
       }
     }

      stage('Sonarqube analysis') {
        environment {
          scannerHome = tool 'sonarqube-scanner'
        }

        steps {
          withSonarQubeEnv(installationName: 'sonarqube') {
            sh '$scannerHome/bin/sonar-scanner'
          }
        }
      }

      stage('Quality Gate') {
        steps {
          timeout(time: 1, unit: 'HOURS') {
            waitForQualityGate abortPipeline: true
          }
        }
      }

      stage('Set Kubeconfig') {
         steps {
            script {
              withCredentials([file(credentialsId: 'ait-k8s_kubeconfig', variable: 'CONFIG')]) {
                sh 'cat ${CONFIG} > ~/.kube/config'
                sh 'export KUBECONFIG=$HOME/.kube/config:$HOME/.kube/ait3-k8s-config'
              }
            }
         }
     }

     stage('Check New Namespace') {
        steps {
            script {
              sh 'kubectl create namespace ${NAMESPACE} --dry-run=client -o yaml | kubectl apply -f -'
            }
        }
     }

     stage('Check Update Secrets') {
        steps {
           script {
             result = sh (script: "git log -1 | grep -E 'update-secret'", returnStatus: true)
             if (result == 0) {
               sh 'kubectl apply -f k8s/secret.yml -n ${NAMESPACE}'
             }
           }
        }
     }

      stage('Build Image - Push - Deploy') {
         steps {
            script {
              withCredentials([file(credentialsId: 'ait-k8s_kubeconfig', variable: 'CONFIG'),
                                           usernamePassword(credentialsId: 'ait-k8s_docker-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                  sh 'docker login ait-cr.akarinti.tech --username=${USER} --password=${PASS}'
                  sh 'mkdir -p $HOME/.kube'
                  sh 'cat ${CONFIG} > ~/.kube/config'
                  sh 'skaffold run -n ${NAMESPACE}'
              }
            }
         }
      }

      stage('Apply k8s') {
          steps {
              script {
                 sh 'kubectl apply -f k8s/depl.yaml -n ${NAMESPACE}'
                 sh 'kubectl apply -f k8s/svc.yaml -n ${NAMESPACE}'
                 sh 'kubectl apply -f k8s/ingress-api.yaml -n ${NAMESPACE}'
//                  sh 'kubectl rollout status -f k8s/depl.yaml -n ${NAMESPACE}'
                 sh 'kubectl get all,ing  -n ${NAMESPACE}'
              }
          }
       }
   }

   post {
     failure {
       emailext subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
         body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
         recipientProviders: [
           [$class: 'DevelopersRecipientProvider'],
           [$class: 'RequesterRecipientProvider']
         ]
     }

     always {
       cleanWs()
     }
   }
}
