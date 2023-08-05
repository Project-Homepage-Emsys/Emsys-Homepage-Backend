package com.emsys.emsyswebsitebackend.service;

import com.emsys.emsyswebsitebackend.domain.User;
import com.emsys.emsyswebsitebackend.dto.UserDto;
import com.emsys.emsyswebsitebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public UserDto saveUser(UserDto userDto) {
        return UserDto.from(userRepository.save(userDto.toEntity()));
    }

    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    public UserDto loginUser(UserDto userDto) {
        Optional<User> user = userRepository.findById(userDto.toEntity().getStudentId());
        if (user.isPresent()) {
            if (user.get().getPassword().equals(userDto.toEntity().getPassword())) {
                return UserDto.from(user.get());
            }
        }
        return null;
    }
}
