package com.mariusar.demospring.elastic;

import com.mariusar.demospring.elastic.content.AppContent;
import com.mariusar.demospring.elastic.content.Content;
import com.mariusar.demospring.elastic.content.ElasticFieldValuePair;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElasticCrudRepository<T extends Content> {

    List<AppContent> searchAppContent(List<ElasticFieldValuePair> fieldValuesPairs);
}
