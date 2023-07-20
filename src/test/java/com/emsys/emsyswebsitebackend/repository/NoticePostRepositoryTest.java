package com.emsys.emsyswebsitebackend.repository;

import com.emsys.emsyswebsitebackend.domain.NoticePost;
import com.emsys.emsyswebsitebackend.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NoticePostRepositoryTest {
    @Autowired
    private NoticePostRepository repo;

//    @Autowired
//    private UserRepository userRepository;

    private User testUser;

    @Test
    public void save(){

//        testUser = userRepository.save(User.of("2019038099", "123123", "helloEmail", "Hello", true, "01083218675" , true, "myoung" , "my"));

        //Given
        NoticePost testNoticePost;
        testNoticePost = new NoticePost(null, "title3", "content3", 0,
                "attachments3", false, "category3");

        //When
        repo.save(testNoticePost);
        List<NoticePost> result = repo.findAll();

        //Then
        assertThat(result).isNotNull().hasSize(3);
    }

    @Test
    public void findById(){

        //given
        NoticePost testNoticePost;
        testNoticePost = new NoticePost(null, "title4", "content4", 0,
                "attachments4", false, "category4");

        //when
        repo.save(testNoticePost);
        NoticePost result = repo.findById(testNoticePost.getId()).get();

        //then

        assertThat(result.getId()).isEqualTo(testNoticePost.getId());
    }

    @Test
    public void findByTitle(){
        //Given
        String title = "title3";

        //When
        List<NoticePost> post = repo.findByTitle(title);

        //then
        assertThat(post.get(0).getId()).isEqualTo(3);
    }

    @Test
    public void deleteById(){

        //given

        //when
        repo.deleteById(4L);
        List<NoticePost> result = repo.findAll();

        //then
        assertThat(result).isNotNull().hasSize(3);
    }

}