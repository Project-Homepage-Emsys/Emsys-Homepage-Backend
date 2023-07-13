package com.emsys.emsyswebsitebackend;

import com.emsys.emsyswebsitebackend.domain.NoticePost;
import com.emsys.emsyswebsitebackend.domain.User;
import com.emsys.emsyswebsitebackend.repository.NoticePostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class NoticePostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NoticePostRepository noticePostRepository;

    @Test
    public void testSaveNoticePost() {
        User user = new User();
        // 필요한 user 필드 설정
    }

}
