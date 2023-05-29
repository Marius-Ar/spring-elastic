package com.mariusar.demospring.rest.controller;

import com.mariusar.demospring.elastic.ContentMapper;
import com.mariusar.demospring.elastic.content.AppContent;
import com.mariusar.demospring.rest.mapper.AppMapper;
import com.mariusar.demospring.rest.model.AppVo;
import com.mariusar.demospring.rest.model.App;
import com.mariusar.demospring.rest.service.AppService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/application")
public class AppController {

    private final AppService appService;

    private final ContentMapper contentMapper;

    private final AppMapper appMapper;

    @PostMapping("/search/keywords")
    public List<AppVo> search(@RequestBody String keywords) {
        List<AppContent> moObjs = appService.searchByKeywords(keywords);
        return contentMapper.toVos(moObjs);
    }

    @PostMapping
    public AppVo create(@RequestBody AppVo appVo) {
        App app = appMapper.toDomain(appVo);
        App saved = appService.create(app);
        return appMapper.toVo(saved);
    }
}
