package com.mariusar.demospring.infrastructure;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.mariusar.demospring.elastic.ElasticCrudRepository;
import com.mariusar.demospring.elastic.RequestFactory;
import com.mariusar.demospring.elastic.content.AppContent;
import com.mariusar.demospring.elastic.content.Content;
import com.mariusar.demospring.elastic.content.ElasticFieldValuePair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class ElasticCrudRepositoryImpl<T extends Content> implements ElasticCrudRepository<T> {

    private final RequestFactory<T> requestFactory;

    private final ElasticsearchClient elasticsearchClient;

    @Override
    public List<AppContent> searchAppContent(List<ElasticFieldValuePair> fieldValuesPairs) {
        SearchRequest request = requestFactory.searchBoolWildcardQuery(ElasticIndexEnum.APP, fieldValuesPairs);
        List<AppContent> results = null;

        try {
            SearchResponse<AppContent> response = elasticsearchClient.search(request, AppContent.class);
            results = response.hits().hits().stream().map(Hit::source).toList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}
