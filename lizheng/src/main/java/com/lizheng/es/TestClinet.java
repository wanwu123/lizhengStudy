package com.lizheng.es;


import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@Slf4j
public class TestClinet {

    public static void main(String[] args) throws Exception{
        //创建客户端
         TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddresses(
                                        new TransportAddress(InetAddress.getByName("127.0.0.1"),9200));
         log.debug("Elasticsearch connect info:" + client.toString());
        //关闭客户端
        client.close();
    }
}
