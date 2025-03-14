package com.devDream.coDream.repository;

import com.devDream.coDream.model.Post;
import com.devDream.coDream.model.User;
import com.devDream.coDream.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
