package com.mariusar.demospring.elastic.content;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import com.mariusar.demospring.elastic.RequestFactory;
import com.mariusar.demospring.infrastructure.ElasticIndexEnum;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContentRequestFactory implements RequestFactory<Content> {

    @Override
    public IndexRequest<Content> createIndexRequest(ElasticIndexEnum index, Content content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }

        return new IndexRequest.Builder<Content>()
                .index(index.getIndexName())
                .document(content)
                .id(content.getId())
                .build();
    }

    @Override
    public SearchRequest searchBoolWildcardQuery(ElasticIndexEnum index, List<ElasticFieldValuePair> fieldValues) {
        SearchRequest.Builder searchRequest = new SearchRequest.Builder().index(index.getIndexName());
        BoolQuery.Builder boolQuery = new BoolQuery.Builder();

        for (ElasticFieldValuePair fieldValue : fieldValues) {
            boolQuery.should(b -> b
                    .wildcard(wb -> wb
                            .field(fieldValue.field())
                            .wildcard(fieldValue.value() + "*")
                    )
            );
        }

        return searchRequest.query(q -> q.bool(boolQuery.build())).build();
    }
}
