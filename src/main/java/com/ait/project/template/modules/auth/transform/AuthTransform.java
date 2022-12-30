package com.ait.project.template.modules.auth.transform;

import com.ait.project.template.modules.auth.dto.response.LoginResponseDTO;
import com.ait.project.template.modules.user.dto.response.UserOBTResponseDTO;
import com.ait.project.template.modules.user.model.entity.UserOBT;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthTransform {

    String createAuthResponse(String token);
}
