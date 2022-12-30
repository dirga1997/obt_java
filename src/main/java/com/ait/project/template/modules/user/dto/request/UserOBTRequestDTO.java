package com.ait.project.template.modules.user.dto.request;

import lombok.Data;

@Data
public class UserOBTRequestDTO {
    private long userId;
    private String userName;
    private String userAddress;
    private String userEmail;

    private String userPassword;
}
