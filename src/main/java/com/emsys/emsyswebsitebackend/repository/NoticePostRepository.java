package com.emsys.emsyswebsitebackend.repository;

import com.emsys.emsyswebsitebackend.domain.NoticePost;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface NoticePostRepository {

    NoticePost save(NoticePost post);
    Optional<NoticePost> findById(Long id);

    List<NoticePost> findAll();

    void deleteById(Long id);

    List<NoticePost> findByTitle(String title);

    List<NoticePost> findByIsImportant(boolean isImportant);

    List<NoticePost> findByCategory(String category);


}
