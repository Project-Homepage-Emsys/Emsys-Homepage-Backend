package com.emsys.emsyswebsitebackend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

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

    protected NoticeComment() {}

    private NoticeComment(String content) {
        this.noticePost = null;
        this.user = null;
        this.content = content;
    }

    public static NoticeComment of(String content) {
        return new NoticeComment(content);
    }
}
