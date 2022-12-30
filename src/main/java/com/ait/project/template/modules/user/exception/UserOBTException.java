package com.ait.project.template.modules.user.exception;

import com.ait.project.template.config.exception.ModuleException;
import com.ait.project.template.shared.constant.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserOBTException extends ModuleException {
    public UserOBTException() {
        super(ResponseEnum.USER_OBT_NOT_FOUND);
        log.error("User not found");
    }
}
