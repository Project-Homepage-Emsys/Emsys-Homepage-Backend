package com.emsys.emsyswebsitebackend.controller;

import com.emsys.emsyswebsitebackend.dto.UserDto;
import com.emsys.emsyswebsitebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username) {
        UserDto user = userService.searchUser(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestParam String studentId,
                                              @RequestParam String password,
                                              @RequestParam String email,
                                              @RequestParam String nickname,
                                              @RequestParam Boolean graduated,
                                              @RequestParam String contact,
                                              @RequestParam Boolean isExecutive,
                                              @RequestParam String githubId,
                                              @RequestParam String baekjoonId) {
        UserDto user = userService.saveUser(studentId, password, email, nickname, graduated, contact, isExecutive, githubId, baekjoonId);
        return ResponseEntity.ok(user);
    }
}
