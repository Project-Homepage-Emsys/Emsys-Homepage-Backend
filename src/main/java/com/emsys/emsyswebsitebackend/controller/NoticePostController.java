package com.emsys.emsyswebsitebackend.controller;

import com.emsys.emsyswebsitebackend.domain.NoticePost;
import com.emsys.emsyswebsitebackend.dto.NoticePostDTO;
import com.emsys.emsyswebsitebackend.service.NoticePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notice")
public class NoticePostController {

    private final NoticePostService noticePostService;

    @Autowired
    public NoticePostController(NoticePostService noticePostService) {
        this.noticePostService = noticePostService;
    }

    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody NoticePostDTO post) {
        System.out.println(post.getId());
        return ResponseEntity.ok(noticePostService.upload(post));
    }

    @GetMapping("post/{id}")
    public ResponseEntity<Optional<NoticePost>> getPostById(@PathVariable Long id) {
        System.out.println(id);
        return ResponseEntity.ok(noticePostService.findById(id));
    }

    @PutMapping("post/{id}")
    public ResponseEntity<Long> updatePost(@PathVariable Long id, @RequestBody NoticePostDTO postDTO) {
        Optional<NoticePost> optional = noticePostService.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(noticePostService.update(id, postDTO));
        }

    }

    @GetMapping
    public ResponseEntity<List<NoticePostDTO>> getAllPosts() {
        return ResponseEntity.ok(noticePostService.findAll());
    }

    @DeleteMapping("post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        noticePostService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("title/{title}")
    public ResponseEntity<List<NoticePostDTO>> getPostByTitle(@PathVariable String title) {
        return ResponseEntity.ok(noticePostService.findByTitle(title));
    }

    @GetMapping("/important/{isImportant}")
    public ResponseEntity<List<NoticePostDTO>> getPostByImportance(@PathVariable boolean isImportant) {
        return ResponseEntity.ok(noticePostService.findByIsImportant(isImportant));
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<NoticePostDTO>> getPostByCategory(@PathVariable String category) {
        return ResponseEntity.ok(noticePostService.findByCategory(category));
    }
}
