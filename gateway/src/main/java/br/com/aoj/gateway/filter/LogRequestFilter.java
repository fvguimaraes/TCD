package br.com.aoj.gateway.filter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class LogRequestFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(LogRequestFilter.class);

    @Autowired
    @Qualifier("PrettyGson")
    private Gson gson;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestId = UUID.randomUUID().toString();

        HttpServletRequest request = ctx.getRequest();

        log.info("RequestID: {} \tProtocol: {} \tURI: {} \tUser-Agent: {}", requestId,request.getProtocol(), request.getRequestURI(), request.getHeader(HttpHeaders.USER_AGENT));
        try {
            StringBuilder sbHeader = new StringBuilder();
            Enumeration<String> headerNames = request.getHeaderNames();
            StringBuilder sBody = new StringBuilder();

            while(headerNames.hasMoreElements()){
                String headerName = headerNames.nextElement();
                sbHeader.append("\n");
                sbHeader.append(headerName);
                sbHeader.append(":\t");
                sbHeader.append(request.getHeader(headerName));
            }

            try {
                if (request.getContentLength() > 0) {
                    Type type = new TypeToken<Map<String, Object>>() {
                    }.getType();
                    Map<String, Object> body = this.gson.fromJson(request.getReader(), type);


                    sBody.append(this.gson.toJson(body));
                }
            }catch (JsonSyntaxException je){

            }

            log.debug("RequestID: {} \tRequest: {}\n{}", requestId,sbHeader,sBody);
        } catch (IOException e) {
            log.debug("RequestID: {} \tError reading original Request body ",requestId,e);
        }


        return null;
    }
}
