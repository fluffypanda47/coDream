package com.devDream.coDream.service;

import com.devDream.coDream.dto.DomainDto;
import com.devDream.coDream.model.Domain;
import com.devDream.coDream.repository.DomainRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class DomainService {

    private final DomainRepository domainRepository;

    @Transactional
    public DomainDto save(DomainDto domainDto) {
        Domain save = domainRepository.save(mapDomainDto(domainDto));
        domainDto.setId(save.getId());
        return domainDto;
    }

    private Domain mapDomainDto(DomainDto domainDto) {
        return Domain.builder().name(domainDto.getName())
                .description(domainDto.getDescription())
                .build();
    }

    @Transactional(readOnly = true)
    public List<DomainDto> getAll() {
        return domainRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    private DomainDto mapToDto(Domain domain) {
        return DomainDto.builder().name(domain.getName())
                .id(domain.getId())
                .numberOfPosts(domain.getPosts().size())
                .build();
    }
}
