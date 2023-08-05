package com.emsys.emsyswebsitebackend.service;

import com.emsys.emsyswebsitebackend.domain.NoticeComment;
import com.emsys.emsyswebsitebackend.domain.User;
import com.emsys.emsyswebsitebackend.dto.NoticeCommentDto;
import com.emsys.emsyswebsitebackend.repository.NoticeCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class NoticeCommentService {

    private final NoticeCommentRepository noticeCommentRepository;

    /**
     * NoticeCommentList(Long)
     * @param noticeId
     * @return 해당 글 전체 댓글 리스트 반환
     */
    @Transactional(readOnly = true)
    public List<NoticeCommentDto> listNoticeComment(Long noticeId) {
        try {
            return noticeCommentRepository.findAllByNoticePost_Id(noticeId)
                    .stream()
                    .map(NoticeCommentDto::from)
                    .toList();
        } catch (Exception e) {
            log.error("에러: " + e.toString());
            return null;
        }
    }

    /**
     * writeNoticeComment(NoticeCommentDto)
     * @param newNoticeCommentDto
     * @return 0 (성공), -1 (실패)
     */
    @Transactional
    public int writeNoticeComment(NoticeCommentDto newNoticeCommentDto) {
        try {
             NoticeComment newNoticeCommentEntity = newNoticeCommentDto.toEntity();
             noticeCommentRepository.save(newNoticeCommentEntity);
            return 0;
        } catch (Exception e) {
            log.error("에러: " + e.toString());
            return -1;
        }
    }

    /**
     * updateNoticeComment(NoticeCommentDto)
     * @param preNoticeCommentDto
     * @return 0 (성공), -1 (실패)
     */
    @Transactional
    public int updateNoticeComment(NoticeCommentDto updatedNoticeCommentDto) {
        try {
            Optional<NoticeComment> fetchExistComment =
                    noticeCommentRepository.findById(updatedNoticeCommentDto.commentId());
            NoticeComment existComment = fetchExistComment.orElse(null);
            NoticeComment updatedComment = updatedNoticeCommentDto.toEntity();
            if (existComment != null && existComment.getCommentId().equals(updatedComment.getCommentId())) {
                noticeCommentRepository.save(updatedComment);
                return 0;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            log.error("에러: " + e.toString());
            return -1;
        }
    }

    /**
     * deleteNoticeComment(Long, User)
     * @param commentId
     * @param user
     * @return 0 (성공), -1 (실패)
     */
    @Transactional
    public int deleteNoticeComment(Long commentId) {
        try {
            noticeCommentRepository.deleteByCommentId(commentId);
            return 0;
        } catch (Exception e) {
            log.error("에러: " + e.toString());
            return -1;
        }
    }
}
