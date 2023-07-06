package com.emsys.emsyswebsitebackend.dto;

import com.emsys.emsyswebsitebackend.domain.User;

public class NoticePostDTO {

    private Long id;
    private User user;
    private String title;
    private String content;
    private int hits;
    private String attachments;
    private String category;

    public NoticePostDTO() {}

    public NoticePostDTO(Long id, User user, String title, String content, int hits, String attachments, boolean isImportant, String category) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.attachments = attachments;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
