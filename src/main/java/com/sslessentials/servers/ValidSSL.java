package com.sslessentials.servers;

public class ValidSSL {

    public static void main(String[] args) throws Exception {
        final ServerWithSSLEnabled server = new ServerWithSSLEnabled("servers/valid/valid.p12");
        server.start();
    }

}
