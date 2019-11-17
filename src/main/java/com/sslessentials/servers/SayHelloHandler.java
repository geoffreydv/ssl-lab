package com.sslessentials.servers;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SayHelloHandler extends AbstractHandler {

    @Override
    public void handle(String targetUrl,
                       Request request,
                       HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.getWriter().write("Hello");
        request.setHandled(true);
    }
}
