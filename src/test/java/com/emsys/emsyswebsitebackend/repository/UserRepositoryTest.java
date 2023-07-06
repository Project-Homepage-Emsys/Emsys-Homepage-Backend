package com.emsys.emsyswebsitebackend.repository;

import com.emsys.emsyswebsitebackend.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createdUserTest() throws Exception {
        // Given
        //유저 생성을 위한 요청 데이터
        String requestBody = "{\"userId\": \"john123\", \"email\": \"john@example.com\", \"nickname\": \"John\"}";

        // When
        //POST 요청으로 유저 생성
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // Then
        //생성된 유저 조회
        User user = userRepository.findById("john123").orElse(null);
        assertNotNull(user);
        assertEquals("john@example.com", user.getEmail());
        assertEquals("John", user.getNickname());
    }
}
