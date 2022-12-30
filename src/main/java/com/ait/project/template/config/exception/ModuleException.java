package com.ait.project.template.config.exception;

import com.ait.project.template.shared.constant.enums.ResponseEnum;
import lombok.Getter;

@Getter
public class ModuleException extends RuntimeException {

  private final ResponseEnum responseEnum;

  public ModuleException(ResponseEnum responseEnum) {
    super(responseEnum.getResponseCode());
    this.responseEnum = responseEnum;
  }
}
