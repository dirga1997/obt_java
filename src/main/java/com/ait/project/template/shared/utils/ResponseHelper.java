package com.ait.project.template.shared.utils;

import java.util.List;

import com.ait.project.template.shared.constant.enums.ResponseEnum;
import com.ait.project.template.shared.transform.ResponseTemplateTransform;
import lombok.RequiredArgsConstructor;
import com.ait.project.template.shared.dto.template.ResponseDetail;
import com.ait.project.template.shared.dto.template.ResponseError;
import com.ait.project.template.shared.dto.template.ResponseList;
import com.ait.project.template.shared.dto.template.ResponseTemplate;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseHelper {

  private final ResponseMessageHelper responseMessageHelper;

  private final ResponseTemplateTransform responseTransform;

  /**
   * a utility function that is used to generate detailed responses based on the AIT Standard
   * @param responseEnum Enum Response
   * @param body Body Return
   * @param <T> Generic Type, for class body return
   * @return ResponseEntity contain Template Response based on AIT Standard
   */
  public <T> ResponseEntity<ResponseTemplate<ResponseDetail<T>>> createResponseDetail(
          ResponseEnum responseEnum, T body) {
    return ResponseEntity.status(responseEnum.getHttpStatus())
        .body(
            responseTransform.templateDetail(responseMessageHelper.getResponseSchema(responseEnum), body)
        );
  }

  public <T> ResponseEntity<ResponseTemplate<T>> createResponseError(
      ResponseEnum responseEnum, T body) {
    return ResponseEntity.status(responseEnum.getHttpStatus())
        .body(
            responseTransform.templateError(responseMessageHelper.getResponseSchema(responseEnum), body)
        );
  }

  public <T> ResponseEntity<ResponseTemplate<ResponseList<T>>> createResponseCollection(
      ResponseEnum responseEnum, Page page,
      List<T> contents) {
    return ResponseEntity.status(responseEnum.getHttpStatus())
        .body(
            responseTransform.templateCollection(responseMessageHelper.getResponseSchema(responseEnum), page,contents)
        );
  }

  public Object createResponseErrorTemplate(ResponseEnum invalidParam, ResponseError responseError) {
    return responseTransform.templateError(responseMessageHelper.getResponseSchema(invalidParam), responseError);
  }
}
