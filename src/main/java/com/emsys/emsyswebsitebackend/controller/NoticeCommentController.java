package com.emsys.emsyswebsitebackend.controller;

import com.emsys.emsyswebsitebackend.domain.NoticePost;
import com.emsys.emsyswebsitebackend.domain.User;
import com.emsys.emsyswebsitebackend.domain.constant.Message;
import com.emsys.emsyswebsitebackend.domain.constant.StatusEnum;
import com.emsys.emsyswebsitebackend.dto.NoticeCommentDto;
import com.emsys.emsyswebsitebackend.dto.UserDto;
import com.emsys.emsyswebsitebackend.request.NoticeCommentRequest;
import com.emsys.emsyswebsitebackend.service.NoticeCommentService;
import com.emsys.emsyswebsitebackend.service.NoticePostService;
import com.emsys.emsyswebsitebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice/{postId}/notice_comment")
public class NoticeCommentController {
    private final NoticeCommentService noticeCommentService;
    private final NoticePostService noticePostService;
    private final UserService userService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<Message> writeNoticeComment(
            @PathVariable Long postId,
            @RequestBody NoticeCommentRequest request
    ) {

        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Optional<NoticePost> fetchNoticePost = noticePostService.findById(postId);
        NoticePost post = fetchNoticePost.orElse(null);
        Optional<UserDto> fetchUser = userService.searchUser(request.getUserName());
        UserDto userDto = fetchUser.orElse(null);

        if (post == null || userDto == null) {
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("존재하지 않는 작성자 혹은 글에 대한 댓글 작성");
        } else {
            User user = userDto.toEntity();
            NoticeCommentDto noticeCommentDto = NoticeCommentDto.of(post, user, request.getContent());

            int result = noticeCommentService.writeNoticeComment(noticeCommentDto);
            if (result == 0) {
                message.setStatus(StatusEnum.OK);
                message.setMessage("댓글 작성 완료");
            } else {
                message.setStatus(StatusEnum.INTERNAL_SERVER_ERROR);
                message.setMessage("댓글 작성 실패");
            }
        }
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    // 해당 글의 댓글 조회
    @GetMapping
    public ResponseEntity<Message> listNoticeComment(
            @PathVariable Long postId)
    {

        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<NoticeCommentDto> noticeComments = noticeCommentService.listNoticeComment(postId);

        message.setStatus(StatusEnum.OK);
        message.setMessage("댓글 조회 완료");
        message.setData(noticeComments);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<Message> updateNoticeComment(
            @PathVariable Long commentId,
            @PathVariable Long postId,
            @RequestParam String content,
            @RequestParam String userName
    ) {

        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Optional<NoticePost> fetchNoticePost = noticePostService.findById(postId);
        NoticePost post = fetchNoticePost.orElse(null);
        User user = userService.searchUser(userName).orElse(null).toEntity();
        NoticeCommentDto noticeCommentDto = NoticeCommentDto.of(commentId, post, user, content);

        int result = noticeCommentService.updateNoticeComment(noticeCommentDto);
        if (result == 0) {
            message.setStatus(StatusEnum.OK);
            message.setMessage("댓글 수정 완료");
        } else {
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("댓글 수정 실패");
        }

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Message> deleteNoticeComment (
            @PathVariable Long commentId)
    {

        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        int result = noticeCommentService.deleteNoticeComment(commentId);
        if (result == 0) {
            message.setStatus(StatusEnum.OK);
            message.setMessage("댓글 삭제 완료");
        } else {
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("댓글 삭제 실패");
        }

        message.setStatus(StatusEnum.OK);
        message.setMessage("댓글 삭제 완료");


        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
