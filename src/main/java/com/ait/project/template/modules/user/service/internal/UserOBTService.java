package com.ait.project.template.modules.user.service.internal;

import com.ait.project.template.modules.user.dto.request.CreateUserOBTOBTRequestDTO;
import com.ait.project.template.modules.user.dto.request.UpdateUserOBTOBTRequestDTO;
import com.ait.project.template.modules.user.dto.response.UserOBTResponseDTO;
import com.ait.project.template.shared.dto.template.ResponseDetail;
import com.ait.project.template.shared.dto.template.ResponseList;
import com.ait.project.template.shared.dto.template.ResponseTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserOBTService {
    ResponseEntity<ResponseTemplate<ResponseList<UserOBTResponseDTO>>> getAllUser();
    ResponseEntity<ResponseTemplate<ResponseDetail<UserOBTResponseDTO>>> getUserById(long userId);

    ResponseEntity<ResponseTemplate<ResponseList<UserOBTResponseDTO>>> getAllUserPage(
            Pageable pageable);

    ResponseEntity<ResponseTemplate<ResponseDetail<UserOBTResponseDTO>>> addUser(CreateUserOBTOBTRequestDTO createUserOBTRequestDTO);

    ResponseEntity<ResponseTemplate<ResponseDetail<UserOBTResponseDTO>>> updateUser(long userId, UpdateUserOBTOBTRequestDTO updateUserRequestDTO);

    ResponseEntity<ResponseTemplate<ResponseDetail<Boolean>>> deleteById(long userId);
}
