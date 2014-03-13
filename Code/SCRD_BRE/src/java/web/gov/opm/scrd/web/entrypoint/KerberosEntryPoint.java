package gov.opm.scrd.web.entrypoint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class KerberosEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOG = Logger.getLogger(KerberosEntryPoint.class);

    
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException ex) throws IOException, ServletException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Sending back Negotiate Header for request: " + request.getRequestURL());
        }
        
        response.addHeader("WWW-Authenticate", "Negotiate");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        StringBuffer sb = new StringBuffer();
        sb.append("<html><meta content=\"0; url=");
        sb.append(buildRedirectUrlToLoginPage(request, response, ex));
        sb.append("\" HTTP-EQUIV=\"REFRESH\"></meta></html>");

        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(sb.toString());
        out.close();
        
        response.flushBuffer();
    }


    private Object buildRedirectUrlToLoginPage(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException ex) {
        return request.getContextPath() + "/login.html";
    }

}
