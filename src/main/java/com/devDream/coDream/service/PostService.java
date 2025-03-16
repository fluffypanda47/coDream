package com.devDream.coDream.service;

import com.devDream.coDream.dto.PostRequest;
import com.devDream.coDream.dto.PostResponse;
import com.devDream.coDream.exceptions.coDreamException;
import com.devDream.coDream.mapper.PostMapper;
import com.devDream.coDream.model.Domain;
import com.devDream.coDream.model.Post;
import com.devDream.coDream.model.User;
import com.devDream.coDream.repository.DomainRepository;
import com.devDream.coDream.repository.PostRepository;
import com.devDream.coDream.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final DomainRepository domainRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        Domain domain = domainRepository.findByName(postRequest.getDomainName())
                .orElseThrow(() -> new coDreamException(postRequest.getDomainName()));
        postRepository.save(postMapper.map(postRequest, domain, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new coDreamException("Post not found for id -" + id));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new coDreamException("User not found for username -" + username));

        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    public List<PostResponse> getPostsByDomain(Long domainId) {
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(()-> new coDreamException("Domain not found by id -" + domainId));
        List<Post> posts = postRepository.findAllByDomain(domain);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }
}
