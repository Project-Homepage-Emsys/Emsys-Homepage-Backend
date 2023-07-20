package com.emsys.emsyswebsitebackend.service;

import com.emsys.emsyswebsitebackend.domain.User;
import com.emsys.emsyswebsitebackend.dto.UserDto;
import com.emsys.emsyswebsitebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<UserDto> searchUser(String username) {
        return userRepository.findById(username)
                .map(UserDto::from);
    }

    public UserDto saveUser(User user) {
        return UserDto.from(userRepository.save(user));
    }

}
