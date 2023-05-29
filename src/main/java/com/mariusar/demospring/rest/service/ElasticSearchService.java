package com.mariusar.demospring.rest.service;

import com.mariusar.demospring.elastic.content.ElasticFieldValuePair;
import com.mariusar.demospring.elastic.content.AppContent;
import com.mariusar.demospring.infrastructure.ElasticCrudRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ElasticSearchService {

    private final ElasticCrudRepositoryImpl<AppContent> elasticCrudRepository;

    public List<AppContent> searchApps(String keywords) {
        return elasticCrudRepository.searchAppContent(buildFieldValuesPairs(List.of("code", "description"), keywords));
    }

    private List<ElasticFieldValuePair> buildFieldValuesPairs(List<String> fields, String keywords) {
        List<ElasticFieldValuePair> results = new ArrayList<>();
        for (String field : fields) {
            results.add(new ElasticFieldValuePair(field, keywords));
        }
        return results;
    }
}
