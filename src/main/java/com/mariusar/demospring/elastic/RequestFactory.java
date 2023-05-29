package com.mariusar.demospring.elastic;

import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.IndexOperation;
import com.mariusar.demospring.elastic.content.Content;
import com.mariusar.demospring.elastic.content.ElasticFieldValuePair;
import com.mariusar.demospring.infrastructure.ElasticIndexEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface RequestFactory<T extends Content> {

    IndexRequest<Content> createIndexRequest(ElasticIndexEnum index, T content);

    SearchRequest searchBoolWildcardQuery(ElasticIndexEnum index, List<ElasticFieldValuePair> fieldValues);

    default BulkRequest createBulkRequest(ElasticIndexEnum index, Iterable<? extends T> documents) {
        BulkRequest.Builder builder = new BulkRequest.Builder().index(index.getIndexName());
        List<BulkOperation> operations = new ArrayList<>();

        for (T document : documents) {
            IndexOperation<Content> indexOperation = new IndexOperation.Builder<Content>()
                    .document(document)
                    .build();
            operations.add(indexOperation._toBulkOperation());
        }

        builder.operations(operations);
        return builder.build();
    }
}
