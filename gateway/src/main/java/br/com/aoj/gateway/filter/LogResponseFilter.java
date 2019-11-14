package br.com.aoj.gateway.filter;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import br.com.aoj.gateway.utils.InputStreamWrapper;

@Component
public class LogResponseFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(LogResponseFilter.class);

    @Autowired
    @Qualifier("PrettyGson")
    private Gson gson;

    @Override
    public String filterType() {
        return "post";
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
        if(!log.isDebugEnabled()){
            return false;
        }

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("RequestID:  \tProtocol: {} \tURI: {} \tUser-Agent: {}",request.getProtocol(), request.getRequestURI(), request.getHeader(HttpHeaders.USER_AGENT));
        try (InputStreamWrapper wrapper = new InputStreamWrapper(ctx.getResponseDataStream())) {
            ctx.setResponseDataStream(wrapper);
            StringBuilder sbHeader = new StringBuilder();
            StringBuilder sBody = new StringBuilder();

            for(Pair<String,String> header : ctx.getOriginResponseHeaders()){
                sbHeader.append("\n");
                sbHeader.append(header.first());
                sbHeader.append(":\t");
                sbHeader.append(header.second());
            }

            try{
//                Type type = new TypeToken<Map<String, Object>>(){}.getType();
//                Map<String, Object> response = this.gson.fromJson(new InputStreamReader(wrapper),type);
//                if(response != null) {
                    //sBody.append(IOUtils.toString(wrapper, StandardCharsets.UTF_8));
                sBody.append(ctx.getResponseBody());
//                }
            }catch (JsonSyntaxException je){
                je.printStackTrace();
            }

            log.debug("RequestID:  \tResponse: {}\n{}",sbHeader,sBody);
        } catch (Exception e) {
            log.error("RequestID:  \tError reading original Response ",e);
        }
        return null;
    }
}
