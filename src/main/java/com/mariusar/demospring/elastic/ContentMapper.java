package com.mariusar.demospring.elastic;

import com.mariusar.demospring.elastic.content.AppContent;
import com.mariusar.demospring.rest.model.AppVo;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    @Named("toVo")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "description", source = "description")
    AppVo toVo(AppContent content);

    @IterableMapping(qualifiedByName = "toVo")
    List<AppVo> toVos(List<AppContent> contents);
}
