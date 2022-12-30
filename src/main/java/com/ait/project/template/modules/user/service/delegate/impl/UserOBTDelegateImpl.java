package com.ait.project.template.modules.user.service.delegate.impl;

import com.ait.project.template.config.repository.DBQM;
import com.ait.project.template.modules.user.exception.UserOBTException;
import com.ait.project.template.modules.user.model.entity.UserOBT;
import com.ait.project.template.modules.user.model.repository.UserOBTRepository;
import com.ait.project.template.modules.user.service.delegate.UserOBTDelegate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;
import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class UserOBTDelegateImpl implements UserOBTDelegate, UserDetailsService {

    private final UserOBTRepository userOBTRepository;
//    private org.springframework.security.core.userdetails.User coreUser;

    @Override
    public UserOBT getUserById(long id) {
        return userOBTRepository.findById(id).orElseThrow(UserOBTException::new);
    }

    @Override
    public UserOBT getUserByEmail(String email) {
        return userOBTRepository.findByUserEmail(email);
    }

    @Override
    public UserOBT getUserByEmailAndPassword(String email, String password) {
        Query query = DBQM.getEntityManager().createQuery("SELECT u.id, u.name, u.address, u.email, u.created_at, u.updated_at FROM user u WHERE u.email = :email AND u.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        return (UserOBT) query.getSingleResult();
    }

    @Override
    public List<UserOBT> getAllUser() {
        return userOBTRepository.findAll();
    }

    @Override
    public Page<UserOBT> getAllUserPage(Pageable pageable) {
        return userOBTRepository.findAll(pageable);
    }

    @Override
    public List<UserOBT> saveAll(List<UserOBT> userOBTList) {
        return userOBTRepository.saveAll(userOBTList);
    }

    @Override
    public UserOBT save(UserOBT userOBT) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userOBT.setUserPassword(bCryptPasswordEncoder.encode(userOBT.getUserPassword()));
        return userOBTRepository.save(userOBT);
    }

    @SneakyThrows
    @Override
    public UserOBT update(long userId, UserOBT userOBT) {
        System.out.println(3142);
        System.out.println(userOBT);
        UserOBT userOBT1 = this.getUserById(userId);
        userOBT1.setUserName(userOBT.getUserName() != null ? userOBT.getUserName() : userOBT1.getUserName());
        userOBT1.setUserAddress(userOBT.getUserAddress() != null ? userOBT.getUserAddress() : userOBT1.getUserAddress());
        userOBT1.setUserEmail(userOBT.getUserEmail() != null ? userOBT.getUserEmail() : userOBT1.getUserEmail());
        userOBT1.setUserPassword(userOBT.getUserPassword() != null ? userOBT.getUserPassword() : userOBT1.getUserPassword());
        System.out.println(userOBT1);
        return this.save(userOBT1);
    }

    @Override
    public Boolean deleteById(long userId) {
        userOBTRepository.deleteById(userId);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserOBT userOBT = this.getUserByEmail(email);
        if (userOBT == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(userOBT.getUserEmail(), userOBT.getUserPassword(), emptyList());
    }
}
