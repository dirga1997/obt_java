package com.ait.project.template.modules.user.service.delegate;

import com.ait.project.template.modules.user.model.entity.UserOBT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserOBTDelegate {

    UserOBT getUserById(long id);

    UserOBT getUserByEmail(String email);

    UserOBT getUserByEmailAndPassword(String email, String password);

    List<UserOBT> getAllUser();

    Page<UserOBT> getAllUserPage(Pageable pageable);

    List<UserOBT> saveAll(List<UserOBT> userOBTList);

    UserOBT save(UserOBT userOBT);

    UserOBT update(long userId, UserOBT updateUserRequestDTOOBT);

    Boolean deleteById(long userId);
}
