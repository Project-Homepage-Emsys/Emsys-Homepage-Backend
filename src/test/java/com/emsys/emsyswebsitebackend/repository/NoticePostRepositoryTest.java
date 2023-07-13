package com.emsys.emsyswebsitebackend.repository;

import com.emsys.emsyswebsitebackend.domain.NoticePost;
import com.emsys.emsyswebsitebackend.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class NoticePostRepositoryTest {
    @Autowired
    private NoticePostRepository repo;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private NoticePost testNoticePost;

    @Test
    public void save(){

        testUser = userRepository.save(Ur.of("2019038099", "123123", "helloEmail", "Hello", true, "01083218675" , true, "myoung" , "my"));


        testNoticePost = new NoticePost(testUser, "title1", "content1", 0,
                "attachments1", false, "category1");

        repo.save(testNoticePost);
        NoticePost result = repo.findById(testNoticePost.getId()).get();
        assertThat(testNoticePost).isEqualTo(result);
    }

}
