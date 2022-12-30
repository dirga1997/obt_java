package com.ait.project.template.modules.user.transform;

import com.ait.project.template.modules.user.dto.request.CreateUserOBTRequestDTO;
import com.ait.project.template.modules.user.dto.request.UpdateUserOBTRequestDTO;
import com.ait.project.template.modules.user.dto.response.UserOBTResponseDTO;
import com.ait.project.template.modules.user.model.entity.UserOBT;
import com.ait.project.template.shared.openfeign.user.request.CreateUserOBTRequest;
import com.ait.project.template.shared.openfeign.user.request.UpdateUserOBTRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserOBTTransform {

    List<UserOBTResponseDTO> createUserResponseList(List<UserOBT> userOBTList);

    List<UserOBT> createUserList(List<UserOBTResponseDTO> userResponseList);

    CreateUserOBTRequest createUserRequest(CreateUserOBTRequestDTO createUserOBTRequestDTO);

    UserOBT createEntityUser(CreateUserOBTRequestDTO createUserOBTRequestDTO);

    UserOBTResponseDTO createUserResponse(UserOBT userOBT);

    default Boolean deleteUserResponse(Boolean b) {
        return null;
    }

    UpdateUserOBTRequest updateUserRequest(UpdateUserOBTRequestDTO updateUserRequestDTO);

    UserOBT updateEntityUser(UpdateUserOBTRequestDTO updateUserRequestDTO);

}
