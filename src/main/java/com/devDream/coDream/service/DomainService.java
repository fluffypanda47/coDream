package com.devDream.coDream.service;

import com.devDream.coDream.dto.DomainDto;
import com.devDream.coDream.exceptions.coDreamException;
import com.devDream.coDream.mapper.DomainMapper;
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
    private final DomainMapper domainMapper;

    @Transactional
    public DomainDto save(DomainDto domainDto) {
        Domain save = domainRepository.save(domainMapper.mapDtoToDomain(domainDto));
        domainDto.setId(save.getId());
        return domainDto;
    }

    @Transactional(readOnly = true)
    public List<DomainDto> getAll() {
        return domainRepository.findAll()
                .stream()
                .map(domainMapper::mapDomainToDto)
                .collect(toList());
    }

    public DomainDto getDomain(Long id) {
        Domain domain = domainRepository.findById(id)
                .orElseThrow(() -> new coDreamException("No Domain found with id"));
        return domainMapper.mapDomainToDto(domain);
    }
}
