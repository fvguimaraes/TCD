package br.com.aoj.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.Debug;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoutingDebugFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(RoutingDebugFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return Debug.debugRouting();
    }

    @Override
    public Object run() {
        String debug = convertToPrettyPrintString(Debug.getRoutingDebug());
        log.info("Routing Debug Info = \n{}", debug);
        return null;
    }

    private String convertToPrettyPrintString(List<String> filterDebugList) {
        StringBuilder strBuilder = new StringBuilder();
        if (filterDebugList != null) {
            for (String msg : filterDebugList) {
                if (msg.startsWith("{")) {
                    strBuilder.append("\t");
                }
                strBuilder.append(msg);
                strBuilder.append("\n");
            }
        }
        return strBuilder.toString();
    }
}
