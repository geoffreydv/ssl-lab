package com.sslessentials.servers;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class ValidSSL {

    public static void main(String[] args) throws Exception {

        final Server server = new Server();

        server.setStopAtShutdown(true);
        server.setHandler(new SayHelloHandler());
        server.setConnectors(new Connector[]{
                createHttpConnector(server),
                createHttpsConnector(server)});
        server.start();
    }

    private static ServerConnector createHttpsConnector(Server server) {

        SslContextFactory sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStoreResource(Resource.newClassPathResource("valid/valid.p12"));
        sslContextFactory.setKeyStorePassword("changeit");

        ServerConnector sslConnector = new ServerConnector(server, sslContextFactory);
        sslConnector.setPort(443);
        return sslConnector;
    }

    private static ServerConnector createHttpConnector(Server server) {
        ServerConnector http = new ServerConnector(server);
        http.setPort(80);
        return http;
    }

}
