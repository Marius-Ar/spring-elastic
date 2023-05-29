package com.mariusar.demospring.elastic;

import com.mariusar.demospring.elastic.content.Content;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticCrudRepository<T extends Content> { }
