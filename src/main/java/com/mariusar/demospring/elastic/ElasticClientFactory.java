package com.mariusar.demospring.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ElasticClientFactory {

    @Value("${es.uris}")
    private String urisString;

    @Value("${es.username}")
    private String username;

    @Value("${es.password}")
    private String password;

    @Bean
    public ElasticsearchClient elasticsearchService() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.username, this.password));

        String[] uris = urisString.split(",");
        HttpHost[] httpHosts = new HttpHost[uris.length];
        for (int i = 0; i < uris.length; ++i) {
            String[] uri = uris[i].split("://");
            String protocol = uri[0];

            String[] hostPort = uri[1].split(":");
            String host = hostPort[0];
            int port = 9200;
            if (hostPort[1] != null) {
                port = Integer.parseInt(hostPort[1]);
            }
            httpHosts[i] = new HttpHost(host, port, protocol);
        }

        RestClient restClient = RestClient.builder(httpHosts)
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider)).build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }
}
