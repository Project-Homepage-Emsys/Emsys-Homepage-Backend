package com.emsys.emsyswebsitebackend.service;

import com.emsys.emsyswebsitebackend.domain.NoticePost;
import com.emsys.emsyswebsitebackend.repository.NoticePostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Transactional
public class NoticePostServiceTest {

    @Autowired NoticePostService noticePostService;
    @Autowired NoticePostRepository noticePostRepository;

    @Test
    public void 게시글_업로드() throws Exception{
        //Given
        NoticePost testPost;
        testPost = new NoticePost(null, "title1", "content1", 0,
                "attachments3", true, "category1");
        //When
        Long saveId = noticePostService.upload(testPost);
        NoticePost findPost = noticePostService.findById(saveId).get();
        //Then
        assertEquals(testPost.getTitle(), findPost.getTitle());
    }
}
