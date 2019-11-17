package com.sslessentials.servers;

import org.eclipse.jetty.server.Server;

public class NoSSL {

    public static void main(String[] args) throws Exception {
        final Server server = new Server(8080);
        server.setStopAtShutdown(true);
        server.setHandler(new SayHelloHandler());
        server.start();
    }

}
