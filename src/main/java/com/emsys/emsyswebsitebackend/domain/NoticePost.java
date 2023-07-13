package com.emsys.emsyswebsitebackend.domain;
import javax.persistence.*;

@Entity
@Table(name = "NoticePost")
public class NoticePost extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "hits")
    private int hits;

    @Column(name = "attachments", nullable = true)
    private String attachments;

    @Column(name = "isImportant")
    private boolean isImportant;

    @Column(name = "category")
    private String category;

    public NoticePost(User user, String title, String content, int hits, String attachments, boolean isImportant, String category) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.isImportant = isImportant;
        this.category = category;
        this.attachments = attachments;
        this.hits = hits;

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

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
