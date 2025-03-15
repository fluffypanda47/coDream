package com.devDream.coDream.controller;

import com.devDream.coDream.dto.DomainDto;
import com.devDream.coDream.service.DomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domain")
@AllArgsConstructor
@Slf4j
public class DomainController {

    private final DomainService domainService;

    @PostMapping
    public ResponseEntity<DomainDto> createDomain(@RequestBody DomainDto domainDto) {
       return  ResponseEntity.status(HttpStatus.CREATED)
                .body(domainService.save(domainDto));
    }

    @GetMapping
    public ResponseEntity<List<DomainDto>> getAllDomains() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(domainService.getAll());
    }
}
