package com.emsys.emsyswebsitebackend.repository;

import com.emsys.emsyswebsitebackend.domain.User;
import com.emsys.emsyswebsitebackend.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        // Create a UserDto object for creating a user
        UserDto userDto = UserDto.of("john123", "pass123", "john@example.com", "John", false, "010-1234-5678", false, "johnGithub", "johnBaekjoon");

        // Convert UserDto object to JSON format
        ObjectMapper objectMapper = new ObjectMapper();
        String userDtoJson = objectMapper.writeValueAsString(userDto);

        // When
        // Send a POST request to create a new user
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // Then
        // Check the created user in the database
        User user = userRepository.findById("john123").orElse(null);

        // Assert the user exists and its properties are correct
        assertNotNull(user);
        assertEquals("john@example.com", user.getEmail());
        assertEquals("John", user.getNickname());
    }
}