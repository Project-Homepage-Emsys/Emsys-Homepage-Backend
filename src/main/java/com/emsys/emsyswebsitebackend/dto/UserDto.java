package com.emsys.emsyswebsitebackend.dto;

import com.emsys.emsyswebsitebackend.domain.User;

import java.time.LocalDateTime;

public record UserDto(
        String studentId,
        String password,
        String email,
        String nickname,
        Boolean graduated,
        String contact,
        Boolean isExecutive,
        String githubId,
        String baekjoonId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) {

    public static UserDto of(String studentId, String password, String email, String nickname, Boolean graduated,
                                    String contact, Boolean isExecutive, String githubId, String baekjoonId) {
        return new UserDto(studentId, password, email, nickname, graduated, contact, isExecutive,
                githubId, baekjoonId, null, null, null, null);
    }

    public static UserDto of(String studentId, String password, String email, String nickname, Boolean graduated,
                                    String contact, Boolean isExecutive, String githubId, String baekjoonId,
                                    LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserDto(studentId, password, email, nickname, graduated, contact, isExecutive,
                githubId, baekjoonId, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserDto from(User entity) {
        return new UserDto(
                entity.getStudentId(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getGraduated(),
                entity.getContact(),
                entity.getIsExecutive(),
                entity.getGithubId(),
                entity.getBaekjoonId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public User toEntity() {
        return User.of(
                studentId,
                password,
                nickname,
                graduated,
                contact,
                email,
                isExecutive,
                githubId,
                baekjoonId
        );
    }
}
