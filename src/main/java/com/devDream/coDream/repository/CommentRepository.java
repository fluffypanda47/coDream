package com.devDream.coDream.repository;

import com.devDream.coDream.model.Comment;
import com.devDream.coDream.model.Post;
import com.devDream.coDream.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
