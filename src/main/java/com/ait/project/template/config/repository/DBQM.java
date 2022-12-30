package com.ait.project.template.config.repository;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@AllArgsConstructor
public class DBQM {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManager() {
        return DBQM.entityManagerFactory.createEntityManager();
    }
}
