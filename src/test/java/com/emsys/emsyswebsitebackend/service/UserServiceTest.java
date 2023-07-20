package com.emsys.emsyswebsitebackend.service;

import com.emsys.emsyswebsitebackend.domain.User;
import com.emsys.emsyswebsitebackend.dto.UserDto;
import com.emsys.emsyswebsitebackend.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 회원")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService sut;

    @Mock
    private UserRepository userRepository;

    @DisplayName("존재하는 회원 ID를 검색하면, 회원 데이터를 Optional로 반환한다.")
    @Test
    void givenExistentUserId_whenSearching_thenReturnsOptionalUserData() {
        // Given
        String username = "uno";
        given(userRepository.findById(username)).willReturn(Optional.of(createUser(username)));

        // When
        Optional<Optional<UserDto>> result = Optional.ofNullable(sut.searchUser(username));

        // Then
        assertThat(result).isPresent();
        then(userRepository).should().findById(username);
    }

    @DisplayName("존재하지 않는 회원 ID를 검색하면, 비어있는 Optional을 반환한다.")
    @Test
    void givenNonexistentUserId_whenSearching_thenReturnsOptionalUserData() {
        // Given
        String username = "wrong-user";
        given(userRepository.findById(username)).willReturn(Optional.empty());

        // When
        Optional<UserDto> result = sut.searchUser(username);

        // Then
        assertThat(result).isEmpty();
        then(userRepository).should().findById(username);
    }

    @DisplayName("회원 정보를 입력하면, 새로운 회원 정보를 저장하여 가입시키고 해당 회원 데이터를 리턴한다.")
    @Test
    void givenUserParams_whenSaving_thenSavesUserAccount() {
        // Given
        User userAccount = createUser("uno");
        User savedUserAccount = createSigningUpUser("uno");
        given(userRepository.save(userAccount)).willReturn(savedUserAccount);

        // When
        UserDto result = sut.saveUser(userAccount);

        // Then
        assertThat(result)
                .hasFieldOrPropertyWithValue("studentId", userAccount.getStudentId())
                .hasFieldOrPropertyWithValue("password", userAccount.getPassword())
                .hasFieldOrPropertyWithValue("email", userAccount.getEmail())
                .hasFieldOrPropertyWithValue("nickname", userAccount.getNickname())
                .hasFieldOrPropertyWithValue("graduated", userAccount.getGraduated())
                .hasFieldOrPropertyWithValue("contact", userAccount.getContact())
                .hasFieldOrPropertyWithValue("isExecutive", userAccount.getIsExecutive())
                .hasFieldOrPropertyWithValue("githubId", userAccount.getGithubId())
                .hasFieldOrPropertyWithValue("baekjoonId", userAccount.getBaekjoonId())
                .hasFieldOrPropertyWithValue("createdBy", userAccount.getCreatedBy())
                .hasFieldOrPropertyWithValue("modifiedBy", userAccount.getModifiedBy());
        then(userRepository).should().save(userAccount);
    }


    private User createUser(String username) {
        return createUser(username, null);
    }

    private User createSigningUpUser(String username) {
        return createUser(username, username);
    }

    private User createUser(String username, String createdBy) {
        UserDto userDto = UserDto.of(
                username,
                "password",
                "e@mail.com",
                "nickname",
                true,
                "contact",
                false,
                "githubId",
                "baekjoonId",
                null,
                createdBy,
                null,
                null
        );
        return userDto.toEntity();
    }

}