package com.emsys.emsyswebsitebackend.controller;

import com.emsys.emsyswebsitebackend.domain.User;
import com.emsys.emsyswebsitebackend.dto.UserDto;
import com.emsys.emsyswebsitebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.findAllUsers();
        return ResponseEntity.ok(userDtos);
    }


    // 새로운 사용자 정보를 저장하는 API
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userdto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userdto));
    }

    // 사용자 로그인 API
    @PostMapping("/login1")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto userdto) {
        return ResponseEntity.ok(userService.loginUser(userdto));
    }

//
//    // 사용자 정보 업데이트 API
//    @PutMapping("/{username}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody User user) {
//        UserDto updatedUserDto = userService.updateUser(username, user);
//        return ResponseEntity.ok(updatedUserDto);
//    }
//
//    // 사용자 삭제 API
//    @DeleteMapping("/{username}")
//    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
//        userService.deleteUser(username);
//        return ResponseEntity.noContent().build();
//    }
}