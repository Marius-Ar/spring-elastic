package com.mariusar.demospring.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ElasticIndexEnum {
    APP("app");

    private final String indexName;
}
