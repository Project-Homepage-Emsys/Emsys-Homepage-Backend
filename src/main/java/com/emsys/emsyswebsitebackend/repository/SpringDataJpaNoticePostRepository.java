package com.emsys.emsyswebsitebackend.repository;

import com.emsys.emsyswebsitebackend.domain.NoticePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaNoticePostRepository extends JpaRepository<NoticePost, Long>, NoticePostRepository {
    @Override
    List<NoticePost> findByTitle(String title);
    @Override
    List<NoticePost> findByIsImportant(boolean isImportant);
    @Override
    List<NoticePost> findByCategory(String category);
}
