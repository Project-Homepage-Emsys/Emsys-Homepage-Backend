package com.emsys.emsyswebsitebackend.controller;

import com.emsys.emsyswebsitebackend.domain.User;
import com.emsys.emsyswebsitebackend.dto.UserDto;
import com.emsys.emsyswebsitebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 특정 사용자 정보를 조회하는 API
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        Optional<UserDto> userDto = userService.searchUser(username);
        return userDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 새로운 사용자 정보를 저장하는 API
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody User user) {
        UserDto savedUserDto = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
    }
}
