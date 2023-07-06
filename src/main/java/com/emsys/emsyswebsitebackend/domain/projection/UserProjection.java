package com.emsys.emsyswebsitebackend.domain.projection;

import com.emsys.emsyswebsitebackend.domain.User;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name ="withoutPassword", types = User.class)
public interface UserProjection {
    String getStudentId();
    String getEmail();
    String getNickname();
    Boolean getGraduated();
    String getContact();
    Boolean getIsExecutive();
    String getGithubId();
    String getBaekjoonId();
    LocalDateTime getCreatedAt();
    String getCreatedBy();
    LocalDateTime getModifiedAt();
    String getModifiedBy();
}