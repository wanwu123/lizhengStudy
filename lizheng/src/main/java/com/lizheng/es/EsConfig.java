package com.lizheng.es;


import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

//@Configuration
public class EsConfig {

/*    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        ElasticsearchTemplate elasticsearchTemplate = new ElasticsearchTemplate(client());
        return elasticsearchTemplate;
    }*/

    @Bean
    public RestHighLevelClient restHighLevelClient() throws UnknownHostException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        return client;
    }

}
