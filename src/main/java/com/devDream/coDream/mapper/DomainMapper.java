package com.devDream.coDream.mapper;

import com.devDream.coDream.dto.DomainDto;
import com.devDream.coDream.model.Domain;
import com.devDream.coDream.model.Post;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DomainMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(domain.getPosts()))")
    DomainDto mapDomainToDto(Domain domain);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Domain mapDtoToDomain(DomainDto domainDto);

}
