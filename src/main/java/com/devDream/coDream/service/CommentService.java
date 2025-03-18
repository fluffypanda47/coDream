package com.devDream.coDream.service;

import com.devDream.coDream.dto.CommentsDto;
import com.devDream.coDream.exceptions.coDreamException;
import com.devDream.coDream.mapper.CommentMapper;
import com.devDream.coDream.model.Comment;
import com.devDream.coDream.model.NotificationEmail;
import com.devDream.coDream.model.Post;
import com.devDream.coDream.model.User;
import com.devDream.coDream.repository.CommentRepository;
import com.devDream.coDream.repository.PostRepository;
import com.devDream.coDream.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;


    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new coDreamException("Post id not found"));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }


    public List<CommentsDto> getAllCommntsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new coDreamException("Post not found with id - " + postId));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).toList();
    }


    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new coDreamException("User not found with username -" + userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto).toList();
    }



}
