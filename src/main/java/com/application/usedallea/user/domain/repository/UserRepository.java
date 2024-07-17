package com.application.usedallea.user.domain.repository;

import com.application.usedallea.user.domain.entity.User;
import com.application.usedallea.user.dto.UserRegisterDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {

    void register(User user);

    void update(User user);

    void remove(User user);

    void deactivate(User user);

    List<User> findAll();

    //User findById(String userId);
    Optional<User> findById(String userId);

    boolean isExistById(String userId);
}
