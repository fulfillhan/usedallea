package com.application.usedallea.user.service;

import com.application.usedallea.old.member.service.MemberServiceImpl;
import com.application.usedallea.user.domain.entity.User;
import com.application.usedallea.user.domain.repository.UserRepository;
import com.application.usedallea.user.dto.UserModifyDto;
import com.application.usedallea.user.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRegisterDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User newUser = User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .nickname(userDto.getNickname())
                .password(encodedPassword)
                .activeYn(userDto.getActiveYn())
                .email(userDto.getEmail())
                .emailStsYn(Optional.ofNullable(userDto.getEmailStsYn()).orElse("n"))
                .phoneNumber(userDto.getPhoneNumber())
                .smsStsYn(Optional.ofNullable(userDto.getSmsStsYn()).orElse("n"))
                .roadAddress(userDto.getRoadAddress())
                .jibunAddress(userDto.getJibunAddress())
                .namujiAddress(userDto.getNamujiAddress())
                .zipCode(userDto.getZipCode())
                .personalInfYn(Optional.ofNullable(userDto.getPersonalInfYn()).orElse("n"))
                .build();
            userRepository.register(newUser);
    }

    @Override
    public void updateUser(UserModifyDto userDto) {
        User user = userRepository.findById(userDto.getUserId()).orElse(null);
        if(user != null){
            User newUser = user.toBuilder()
                    .nickname(userDto.getNickname())
                    .smsStsYn(Optional.ofNullable(userDto.getSmsStsYn()).orElse("n"))
                    .phoneNumber(userDto.getPhoneNumber())
                    .email(userDto.getEmail())
                    .emailStsYn(Optional.ofNullable(userDto.getEmailStsYn()).orElse("n"))
                    .zipCode(userDto.getZipCode())
                    .roadAddress(userDto.getRoadAddress())
                    .jibunAddress(userDto.getJibunAddress())
                    .namujiAddress(userDto.getNamujiAddress())
                    .build();
            userRepository.update(newUser);
        }else throw new UsernameNotFoundException(userDto.getUserId()+"is not found");
    }

    @Override
    public void deactivateUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            User updateUser = user.toBuilder()
                    .activeYn("n")
                    .updatedAt(user.getUpdatedAt())
                    .build();
            userRepository.update(updateUser);
        }
    }

    @Override
    public boolean loginUser(UserRegisterDto userDto) {
        String userId = userDto.getUserId();
        User foundUser = userRepository.findById(userId).orElse(null);
        if(foundUser != null){
            return passwordEncoder.matches(userDto.getPassword(), foundUser.getPassword()) &&
                    foundUser.getActiveYn().equals("y");
        }
        return false;
    }

@Override
public boolean checkDuplicatedUser(String userId) {
    // 중복 여부
    return userRepository.isExistById(userId);

    }
}
