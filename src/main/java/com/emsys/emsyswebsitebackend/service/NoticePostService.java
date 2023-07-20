package com.emsys.emsyswebsitebackend.service;

import com.emsys.emsyswebsitebackend.domain.NoticePost;
import com.emsys.emsyswebsitebackend.dto.NoticePostDTO;
import com.emsys.emsyswebsitebackend.repository.NoticePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoticePostService {

    private final NoticePostRepository noticePostRepository;

    @Autowired
    public NoticePostService(NoticePostRepository noticePostRepository) {
        this.noticePostRepository = noticePostRepository;
    }

    public long upload(NoticePostDTO post) {
        NoticePost noticePost = post.toEntity();
        noticePostRepository.save(noticePost);
        return noticePost.getId();
    }

    public long update(Long id, NoticePostDTO postDTO){
        Optional<NoticePost> optional = noticePostRepository.findById(id);
        NoticePost existingPost = optional.get();
        existingPost.setUser(postDTO.getUser());
        existingPost.setTitle(postDTO.getTitle());
        existingPost.setContent(postDTO.getContent());
        existingPost.setHits(postDTO.getHits());
        existingPost.setAttachments(postDTO.getAttachments());
        existingPost.setCategory(postDTO.getCategory());
        noticePostRepository.save(existingPost);

        return existingPost.getId();
    }

    public Optional<NoticePost> findById(Long id) {
        return noticePostRepository.findById(id);
    }

    public List<NoticePostDTO> findAll() {
        return noticePostRepository.findAll().stream()
                .map(NoticePost::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        noticePostRepository.deleteById(id);
    }

    public List<NoticePostDTO> findByTitle(String title) {
        return noticePostRepository.findByTitle(title).stream()
                .map(NoticePost::toDTO)
                .collect(Collectors.toList());
    }

    public List<NoticePostDTO> findByIsImportant(boolean isImportant) {
        return noticePostRepository.findByIsImportant(isImportant).stream()
                .map(NoticePost::toDTO)
                .collect(Collectors.toList());
    }

    public List<NoticePostDTO> findByCategory(String category) {
        return noticePostRepository.findByCategory(category).stream()
                .map(NoticePost::toDTO)
                .collect(Collectors.toList());
    }
}
