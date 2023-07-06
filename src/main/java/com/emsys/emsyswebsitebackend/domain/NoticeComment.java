package com.emsys.emsyswebsitebackend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
// @Table
@Entity
public class NoticeComment extends AuditingFields {
    @Id
    @Column()
    private String commentId;      // PK // commentNo?

    @JoinColumn(name="noticeId")
    private Long noticeId;  // FK, reference to Article(articleId)

    @ManyToOne
    @JoinColumn(name="writeUserId")
    private User user;          // FK, reference to User(studnetId)

    @Lob
    @Column(nullable = false)
    private String content;

    // 생성일자, 수정일자 fields

}
