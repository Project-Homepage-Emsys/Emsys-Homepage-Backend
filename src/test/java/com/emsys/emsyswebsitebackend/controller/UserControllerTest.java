package com.emsys.emsyswebsitebackend.controller;

import com.emsys.emsyswebsitebackend.dto.UserDto;
import com.emsys.emsyswebsitebackend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUserByUsername() throws Exception {
        UserDto user = UserDto.of("studentId", "password", "email", "nickname", true, "contact", false, "githubId", "baekjoonId", LocalDateTime.now(), "createdBy", LocalDateTime.now(), "modifiedBy");
        when(userService.searchUser(any(String.class))).thenReturn(user);

        mockMvc.perform(get("/api/users/testuser"))
                .andExpect(status().isOk());
    }

    @Test
    public void createUser() throws Exception {
        UserDto user = UserDto.of("studentId", "password", "email", "nickname", true, "contact", false, "githubId", "baekjoonId", LocalDateTime.now(), "createdBy", LocalDateTime.now(), "modifiedBy");
        when(userService.saveUser(any(String.class), any(String.class), any(String.class), any(String.class), any(Boolean.class), any(String.class), any(Boolean.class), any(String.class), any(String.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .param("studentId", "testId")
                        .param("password", "testPassword")
                        .param("email", "testEmail@test.com")
                        .param("nickname", "testNickname")
                        .param("graduated", "false")
                        .param("contact", "testContact")
                        .param("isExecutive", "false")
                        .param("githubId", "testGithubId")
                        .param("baekjoonId", "testBaekjoonId")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
