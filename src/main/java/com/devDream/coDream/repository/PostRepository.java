package com.devDream.coDream.repository;

import com.devDream.coDream.model.Domain;
import com.devDream.coDream.model.Post;
import com.devDream.coDream.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByDomain(Domain domain);

    List<Post> findByUser(User user);
}
