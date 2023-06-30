package com.emsys.emsyswebsitebackend.repository;

import com.emsys.emsyswebsitebackend.domain.NoticePost;

import java.util.List;
import java.util.Optional;

public interface NoticePostRepository {

    NoticePost save(NoticePost post);

    Optional<NoticePost> findById(Long id);

    List<NoticePost> findAll();

    void deleteById(Long id);

    Optional<NoticePost> findByUser_Id(Long userId);

    Optional<NoticePost> findByTitle(String title);

    Optional<NoticePost> findByIsImportant(boolean isImportant);

    Optional<NoticePost> findByCategory(String category);


}
