package com.emsys.emsyswebsitebackend.repository;

import com.emsys.emsyswebsitebackend.config.JpaConfig;
import com.emsys.emsyswebsitebackend.domain.NoticePost;
import com.emsys.emsyswebsitebackend.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@SpringBootTest
@Transactional
public class JpaRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    private NoticePostRepository repo;

    private User testUser;
    private NoticePost testNoticePost;

    JpaRepositoryTest(
            @Autowired UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<User> users = userRepository.findAll();

        // Then
        assertThat(users)
                .isNotNull()
                .hasSize(25);
    }

    @DisplayName("post save, select 테스트")
    @Test
    public void save() {

        testUser = userRepository.save(User.of("2019038099", "123123", "helloEmail", "Hello", true, "01083218675", true, "myoung", "my"));
        testNoticePost = new NoticePost(testUser, "title2", "content2", 0,
                "attachments2", false, "category2");
        System.out.println(testNoticePost);

        repo.save(testNoticePost);
        List<NoticePost> result = repo.findAll();

        System.out.println(result);

        assertThat(result).isNotNull().hasSize(1);
    }



    @DisplayName("Insert 테스트")
    @Test
    void givenUser_whenSaving_thenUserIsInDatabase() {
        // Given
        long previousCount = userRepository.count();
        User user = userRepository.save(User.of("2019038099", "123123", "helloEmail", "Hello", true, "01083218675", true, "myoung", "my"));


        // When
        User savedUser = userRepository.save(user);

        // Then
        assertThat(userRepository.count()).isEqualTo(previousCount + 1);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getStudentId()).isEqualTo(user.getStudentId());
        assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
        // 나머지 필드들에 대한 검증도 해주세요.
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        String studentId = "20230001"; //변경하려는 User의 studentId를 사용합니다
        User originalUser = userRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        String originalEmail = originalUser.getEmail();

        String newEmail = "updatedEmail@email.com"; //originalUser의 정보를 변경
        originalUser.setEmail(newEmail);

        // When
        User updatedUser = userRepository.saveAndFlush(originalUser);


        // Then
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getEmail()).isEqualTo(newEmail);
        assertThat(updatedUser.getEmail()).isNotEqualTo(originalEmail);
    }

    @DisplayName("Delete 테스트")
    @Test
    void givenUser_whenDeleting_thenUserIsDeleted() {
        // Given
        String studentId = "20230001"; // 삭제하려는 User의 studentId를 사용합니다.
        User userToDelete = userRepository.findById(studentId).orElseThrow(
                () -> new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다.")
        );

        // When
        userRepository.delete(userToDelete);
        userRepository.flush(); // DB의 상태를 즉시 갱신합니다.

        // Then
        Optional<User> deletedUser = userRepository.findById(studentId);
        assertThat(deletedUser.isPresent()).isFalse();
    }


//    @EnableJpaAuditing
//    @TestConfiguration
//    static class TestJpaConfig {
//        @Bean
//        AuditorAware<String> auditorAware() {
//            return () -> Optional.of("uno");
//        }
//    }
}