package com.ait.project.template.modules.user.model.repository;

import com.ait.project.template.modules.user.model.entity.UserOBT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOBTRepository extends JpaRepository<UserOBT, Long> {
    UserOBT findByUserEmail(String email);
}
