package com.emsys.emsyswebsitebackend.service;

import com.emsys.emsyswebsitebackend.domain.User;
import com.emsys.emsyswebsitebackend.dto.UserDto;
import com.emsys.emsyswebsitebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDto searchUser(String username) {
        return UserDto.from(Objects.requireNonNull(userRepository.findById((username)).orElse(null)));
    }

    public UserDto saveUser(String studentId, String password, String email, String nickname, Boolean graduated, String contact, Boolean isExecutive, String githubId, String baekjoonId) {
        return UserDto.from(
                userRepository.save(User.of(studentId, password, email, nickname, graduated, contact, isExecutive, githubId, baekjoonId)));
    }

}
