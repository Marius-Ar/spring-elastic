package com.mariusar.demospring.rest.mapper;

import com.mariusar.demospring.rest.model.App;
import com.mariusar.demospring.rest.model.AppVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppMapper {

    @Mapping(target = "code", source = "code")
    @Mapping(target = "description", source = "description")
    AppVo toVo(App app);

    @Mapping(target = "code", source = "code")
    @Mapping(target = "description", source = "description")
    App toDomain(AppVo appVo);
}
