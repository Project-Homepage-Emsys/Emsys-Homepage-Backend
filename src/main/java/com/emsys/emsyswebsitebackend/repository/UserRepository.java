package com.emsys.emsyswebsitebackend.repository;

import com.emsys.emsyswebsitebackend.domain.User;
import com.emsys.emsyswebsitebackend.domain.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = UserProjection.class)
public interface UserRepository extends JpaRepository<User, String> {
}
