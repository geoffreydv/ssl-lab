package com.sslessentials.servers;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class ServerWithSSLEnabled {

    private final Server server;

    public ServerWithSSLEnabled(String keystoreClasspathReference) {

        server = new Server();

        server.setStopAtShutdown(true);
        server.setHandler(new SayHelloHandler(true));
        server.setConnectors(new Connector[]{
                createHttpConnector(server),
                createHttpsConnector(server, keystoreClasspathReference)});
    }

    public void start() throws Exception {
        server.start();
    }

    private static ServerConnector createHttpsConnector(Server server, String keystoreClasspathReference) {

        SslContextFactory sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStoreResource(Resource.newClassPathResource(keystoreClasspathReference));
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
