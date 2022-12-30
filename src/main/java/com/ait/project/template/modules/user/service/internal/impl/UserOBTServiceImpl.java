package com.ait.project.template.modules.user.service.internal.impl;

import com.ait.project.template.modules.user.dto.request.CreateUserOBTOBTRequestDTO;
import com.ait.project.template.modules.user.dto.request.UpdateUserOBTOBTRequestDTO;
import com.ait.project.template.modules.user.dto.response.UserOBTResponseDTO;
import com.ait.project.template.modules.user.model.entity.UserOBT;
import com.ait.project.template.modules.user.service.delegate.UserOBTDelegate;
import com.ait.project.template.modules.user.service.internal.UserOBTService;
import com.ait.project.template.modules.user.transform.UserOBTTransform;
import com.ait.project.template.shared.constant.enums.ResponseEnum;
import com.ait.project.template.shared.dto.template.ResponseDetail;
import com.ait.project.template.shared.dto.template.ResponseList;
import com.ait.project.template.shared.dto.template.ResponseTemplate;
import com.ait.project.template.shared.openfeign.user.UserOBTClient;
import com.ait.project.template.shared.utils.ResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOBTServiceImpl implements UserOBTService {

    private final ResponseHelper responseHelper;
    private final UserOBTDelegate userOBTDelegate;

    private final UserOBTClient userOBTClient;

    private final UserOBTTransform userOBTTransform;

    @Override
    public ResponseEntity<ResponseTemplate<ResponseList<UserOBTResponseDTO>>> getAllUser() {
        List<UserOBT> userOBTList = userOBTDelegate.getAllUser();

        if (userOBTList.isEmpty()) {
            List<UserOBTResponseDTO> userResponseList = userOBTClient.getListUser();
            if (!userResponseList.isEmpty()) {
                userOBTList =
                        userOBTDelegate.saveAll(userOBTTransform.createUserList(userResponseList));
            }
        }

        return responseHelper.createResponseCollection(ResponseEnum.SUCCESS, null,
                userOBTTransform.createUserResponseList(userOBTList));
    }

    @Override
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserOBTResponseDTO>>> getUserById(long userId) {
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, userOBTTransform.createUserResponse(userOBTDelegate.getUserById(userId)));
    }

    @Override
    public ResponseEntity<ResponseTemplate<ResponseList<UserOBTResponseDTO>>> getAllUserPage(Pageable pageable) {
        Page<UserOBT> userPage = userOBTDelegate.getAllUserPage(pageable);

        return responseHelper.createResponseCollection(ResponseEnum.SUCCESS, userPage,
                userOBTTransform.createUserResponseList(userPage.getContent()));
    }

    @Override
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserOBTResponseDTO>>> addUser(CreateUserOBTOBTRequestDTO createUserOBTRequestDTO) {
        userOBTClient.createUser(userOBTTransform.createUserRequest(createUserOBTRequestDTO));
        UserOBT userOBT =
                userOBTDelegate.save(userOBTTransform.createEntityUser(createUserOBTRequestDTO));
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS,
                userOBTTransform.createUserResponse(userOBT));
    }

    @Override
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserOBTResponseDTO>>> updateUser(long userId, UpdateUserOBTOBTRequestDTO updateUserRequestDTO) {
        userOBTClient.updateUser(userOBTTransform.updateUserRequest(updateUserRequestDTO));
        UserOBT userOBT =
                userOBTDelegate.update(userId, userOBTTransform.updateEntityUser(updateUserRequestDTO));
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS,
                userOBTTransform.createUserResponse(userOBT));
    }

    @Override
    public ResponseEntity<ResponseTemplate<ResponseDetail<Boolean>>> deleteById(long userId) {
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, userOBTTransform.deleteUserResponse(userOBTDelegate.deleteById(userId)));
    }
}
