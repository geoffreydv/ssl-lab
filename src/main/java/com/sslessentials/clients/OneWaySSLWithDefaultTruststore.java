package com.sslessentials.clients;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.IOException;

public class OneWaySSLWithDefaultTruststore {

    public static void main(String[] args) throws IOException {

        SSLContext sslcontext = getSSLContext();

        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext, new DefaultHostnameVerifier());
        CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(factory).build();

        final CloseableHttpResponse response = client.execute(new HttpGet("https://localhost/"));
        System.out.println(new BasicResponseHandler().handleResponse(response));
    }

    private static SSLContext getSSLContext() {
        return SSLContexts.createSystemDefault();
    }
}
