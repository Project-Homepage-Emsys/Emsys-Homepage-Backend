package com.emsys.emsyswebsitebackend.repository;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoticeCommentRepositoryTest {

    @Autowired
    private NoticeCommentRepository noticeCommentRepository;


    @DisplayName("공지글 ID를 통해, 해당 글의 댓글 리스트를 조회한다.")
    @Test
    void GivenNoticePostId_WhenSearchingNoticeComments_ThenReturnNoticeComments() {
        // Given
        Long noticeId = 1L;

        // When


        // Then

    }

}
