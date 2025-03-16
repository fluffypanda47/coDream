package com.devDream.coDream.mapper;


import com.devDream.coDream.dto.PostRequest;
import com.devDream.coDream.dto.PostResponse;
import com.devDream.coDream.model.*;
import com.devDream.coDream.repository.CommentRepository;
import com.devDream.coDream.repository.VoteRepository;
import com.devDream.coDream.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.devDream.coDream.model.VoteType.DOWNVOTE;
import static com.devDream.coDream.model.VoteType.UPVOTE;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "voteCount", constant = "0")
    Post map(PostRequest postRequest, Domain domain, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "domainName", source = "domain.name")
    @Mapping(target = "userName", source = "user.username")
//    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
//    @Mapping(target = "duration", expression = "java(getDuration(post))")
//    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
//    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    PostResponse mapToDto(Post post);



}