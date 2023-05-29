package com.mariusar.demospring.rest.service;

import com.mariusar.demospring.elastic.content.AppContent;
import com.mariusar.demospring.rest.model.App;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AppService {

    private ElasticSearchService elasticSearchService;

    public List<AppContent> searchByKeywords(String keywords) {
        return elasticSearchService.searchApps(keywords);
    }

    public App create(App app) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
