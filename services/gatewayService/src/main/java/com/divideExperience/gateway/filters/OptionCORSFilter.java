package com.divideExperience.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public class OptionCORSFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
        HttpServletResponse res = RequestContext.getCurrentContext().getResponse();
        if (((HttpServletRequest) req).getMethod().equals("OPTIONS")) {
            res.setStatus(200);
        }
        return null;
    }
}
