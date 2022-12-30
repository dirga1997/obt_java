package com.ait.project.template.shared.openfeign.user;

import com.ait.project.template.modules.user.dto.response.UserOBTResponseDTO;
import com.ait.project.template.shared.openfeign.user.request.CreateUserOBTRequest;
import com.ait.project.template.shared.openfeign.user.request.UpdateUserOBTRequest;
import com.ait.project.template.shared.openfeign.user.response.UserOBTResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserOBTClientFallback implements UserOBTClient {

    @Override
    public List<UserOBTResponseDTO> getListUser() {
        return null;
    }

    @Override
    public UserOBTResponse createUser(CreateUserOBTRequest createUserOBTRequest) {
        return null;
    }

    @Override
    public UserOBTResponse updateUser(UpdateUserOBTRequest updateUserRequest) {
        return null;
    }
}
