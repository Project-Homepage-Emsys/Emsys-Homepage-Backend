package com.emsys.emsyswebsitebackend.domain;

import com.emsys.emsyswebsitebackend.dto.NoticeCommentDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@Table(name="NOTICE_COMMENT")
@Entity
public class NoticeComment extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="commentId")
    private Long commentId;             // PK

    @ManyToOne
    @JoinColumn(name="noticeId")
    private NoticePost noticePost;      // FK, reference to NoticePost(id)

    @ManyToOne
    @JoinColumn(name="writeUserId")
    private User user;                  // FK, reference to User(studentId)

    @Column(nullable = false)
    private String content;

    // createdAt, createdBy, modifiedAt, modifiedBy


    public static NoticeComment of(String content) {
        return new NoticeComment(content);
    }

    public static NoticeComment of(Long commentId, NoticePost noticePost, User user, String content) {
        return new NoticeComment(commentId, noticePost, user, content);
    }

    public static NoticeCommentDto toDto(NoticeComment entity) {
        return NoticeCommentDto.from(entity);
    }

    private NoticeComment(Long commentId, NoticePost noticePost, User user, String content) {
        this.commentId = commentId;
        this.noticePost = noticePost;
        this.user = user;
        this.content = content;
    }

    private NoticeComment(NoticePost noticePost, User user, String content) {
        this.noticePost = noticePost;
        this.user = user;
        this.content = content;
    }

    private NoticeComment(String content) {
        this.noticePost = null;
        this.user = null;
        this.content = content;
    }

    private NoticeComment(User user, String content) {
        this.noticePost = null;
        this.user = user;
        this.content = content;
    }

    private NoticeComment(NoticePost noticePost, String content) {
        this.noticePost = noticePost;
        this.user = null;
        this.content = content;
    }
}
