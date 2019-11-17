package com.sslessentials.servers;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SayHelloHandler extends AbstractHandler {

    private final boolean enforceSsl;

    public SayHelloHandler(boolean enforceSsl) {
        this.enforceSsl = enforceSsl;
    }

    @Override
    public void handle(String targetUrl,
                       Request request,
                       HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) throws IOException {

        request.setHandled(true);

        if (enforceSsl && !"https".equals(request.getScheme())) {
            httpServletResponse.sendRedirect("https://" + httpServletRequest.getServerName() + targetUrl);
            return;
        }

        httpServletResponse.getWriter().write("Hello");
    }
}
