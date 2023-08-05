package com.emsys.emsyswebsitebackend.repository;

import com.emsys.emsyswebsitebackend.domain.NoticeComment;
import com.emsys.emsyswebsitebackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Long> {

    // 전체 댓글 리스트 조회
    List<NoticeComment> findAll();

    // 해당 글 댓글 전체 조회
    List<NoticeComment> findAllByNoticePost_Id(Long noticePostId);

    // comment id 로 댓글 조회

    // 댓글 삭제
    void deleteByCommentId(Long commentId);
}
