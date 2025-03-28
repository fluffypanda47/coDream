package com.devDream.coDream.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DomainDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
