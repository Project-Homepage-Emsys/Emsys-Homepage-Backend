//package com.emsys.emsyswebsitebackend.repository;
//
//import com.emsys.emsyswebsitebackend.config.JpaConfig;
//import com.emsys.emsyswebsitebackend.domain.User;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.DisplayName;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Import;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DisplayName("JPA 연결 테스트")
//@Import(JpaConfig.class)
//@SpringBootTest
//public class JpaRepositoryTest {
//
//    private final UserRepository userRepository;
//
//    JpaRepositoryTest(
//            @Autowired UserRepository userRepository
//    ) {
//        this.userRepository = userRepository;
//    }
//
//    @DisplayName("select 테스트")
//    @Test
//    void givenTestData_whenSelecting_thenWorksFine() {
//        // Given
//
//        // When
//        List<User> users = userRepository.findAll();
//
//        // Then
//        assertThat(users)
//                .isNotNull()
//                .hasSize(1);
//    }
//
//
//
////    @EnableJpaAuditing
////    @TestConfiguration
////    static class TestJpaConfig {
////        @Bean
////        AuditorAware<String> auditorAware() {
////            return () -> Optional.of("uno");
////        }
////    }
//}