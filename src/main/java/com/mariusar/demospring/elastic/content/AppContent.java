package com.mariusar.demospring.elastic.content;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppContent extends Content {

    private String code;
    private String description;
}
