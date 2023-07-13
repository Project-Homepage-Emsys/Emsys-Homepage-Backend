package com.emsys.emsyswebsitebackend.dto;

import com.emsys.emsyswebsitebackend.domain.NoticeComment;
import com.emsys.emsyswebsitebackend.domain.NoticePost;
import com.emsys.emsyswebsitebackend.domain.User;

import java.time.LocalDateTime;

public record NoticeCommentDto(
    Long commentId,
    NoticePost noticePost,
    User user,
    String content,

    LocalDateTime createdAt,
    String createdBy,
    LocalDateTime modifiedAt,
    String modifiedBy
) {

    public static NoticeCommentDto of(Long commentId, NoticePost noticePost, User user, String content) {
        return new NoticeCommentDto(commentId, noticePost, user, content, null, null, null, null);
    }

    public static NoticeCommentDto of(Long commentId, NoticePost noticePost, User user, String content,
                             LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new NoticeCommentDto(commentId, noticePost, user, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static NoticeCommentDto from(NoticeComment entity) {
        return new NoticeCommentDto(
            entity.getCommentId(),
            entity.getNoticePost(),
            entity.getUser(),
            entity.getContent(),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getModifiedAt(),
            entity.getModifiedBy()
        );
    }

//    public NoticeComment toEntity() {
//        return NoticeComment.of(
//                this.commentId,
//                this.noticePost,
//                this.user,
//                this.content,
//                this.createdAt,
//                this.createdBy,
//                this.modifiedAt,
//                this.modifiedBy
//        );
//    }
}
