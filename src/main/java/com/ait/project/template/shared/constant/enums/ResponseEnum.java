package com.ait.project.template.shared.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseEnum {
  SUCCESS("OBT-200", "success", HttpStatus.OK),

  USER_OBT_NOT_FOUND("OBT-404", "user.not.found", HttpStatus.NOT_FOUND),

  INVALID_PARAM("OBT-403", "invalid.param", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR("OBT-500", "internal.server.error", HttpStatus.INTERNAL_SERVER_ERROR),

  ;

  private String responseCode;
  private String responseMessage;
  private HttpStatus httpStatus;

}
