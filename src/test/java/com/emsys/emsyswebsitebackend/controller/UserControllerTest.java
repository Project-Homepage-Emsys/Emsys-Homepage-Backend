package com.emsys.emsyswebsitebackend.controller;

import com.emsys.emsyswebsitebackend.domain.User;
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
import org.springframework.test.web.servlet.ResultActions;

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
        when(userService.searchUser(any(String.class))).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/testuser"))
                .andExpect(status().isOk());
    }

    @Test
    public void createUser() throws Exception {
        // given
        User user = User.of("studentId", "password", "email", "nickname", true, "contact", false, "githubId", "baekjoonId");
        UserDto userDto = UserDto.from(user);
        when(userService.saveUser(user)).thenReturn(userDto);

        String requestBody = "{ \"studentId\": \"testId\", \"password\": \"testPassword\", \"email\": \"testEmail@test.com\", \"nickname\": \"testNickname\", \"graduated\": false, \"contact\": \"testContact\", \"isExecutive\": false, \"githubId\": \"testGithubId\", \"baekjoonId\": \"testBaekjoonId\" }";

        // when
        ResultActions result = mockMvc.perform(post("/api/users")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isCreated());

    }



}
