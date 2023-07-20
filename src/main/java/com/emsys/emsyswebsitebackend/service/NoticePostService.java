package com.emsys.emsyswebsitebackend.service;

import com.emsys.emsyswebsitebackend.domain.NoticePost;
import com.emsys.emsyswebsitebackend.repository.NoticePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticePostService {

    private final NoticePostRepository noticePostRepository;

    @Autowired
    public NoticePostService(NoticePostRepository noticePostRepository) {
        this.noticePostRepository = noticePostRepository;
    }

    public long upload(NoticePost post) {
        noticePostRepository.save(post);
        return post.getId();
    }

    public Optional<NoticePost> findById(Long id) {
        return noticePostRepository.findById(id);
    }

    public List<NoticePost> findAll() {
        return noticePostRepository.findAll();
    }

    public void deleteById(Long id) {
        noticePostRepository.deleteById(id);
    }

    public List<NoticePost> findByTitle(String title) {
        return noticePostRepository.findByTitle(title);
    }

    public List<NoticePost> findByIsImportant(boolean isImportant) {
        return noticePostRepository.findByIsImportant(isImportant);
    }

    public List<NoticePost> findByCategory(String category) {
        return noticePostRepository.findByCategory(category);
    }
}
