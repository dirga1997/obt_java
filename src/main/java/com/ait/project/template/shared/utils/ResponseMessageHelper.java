package com.ait.project.template.shared.utils;

import java.util.Locale;

import com.ait.project.template.shared.constant.enums.ResponseEnum;
import lombok.RequiredArgsConstructor;
import com.ait.project.template.shared.dto.template.ResponseSchema;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseMessageHelper {

  private final MessageSource responseMessageSource;

  public ResponseSchema getResponseSchema(ResponseEnum responseEnum) {
    return new ResponseSchema(responseEnum.getResponseCode(),
        getMessage(responseEnum.getResponseMessage()));
  }

  private String getMessage(String code) {
    return responseMessageSource.getMessage(code, null, Locale.getDefault());
  }
}
