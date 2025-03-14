package com.devDream.coDream.repository;

import com.devDream.coDream.model.Post;
import com.devDream.coDream.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository {
//    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
