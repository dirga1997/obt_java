package com.ait.project.template.shared.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseEnum {
  SUCCESS("OBT-" + HttpStatus.OK, "success", HttpStatus.OK),

  USER_OBT_NOT_FOUND("OBT-" + HttpStatus.NOT_FOUND, "user.not.found", HttpStatus.NOT_FOUND),

  INVALID_PARAM("OBT-" + HttpStatus.BAD_REQUEST, "invalid.param", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR("OBT-" + HttpStatus.INTERNAL_SERVER_ERROR, "internal.server.error", HttpStatus.INTERNAL_SERVER_ERROR),

  ;

  private String responseCode;
  private String responseMessage;
  private HttpStatus httpStatus;

}
