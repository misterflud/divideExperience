package com.divideExperience.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;

public class PreRequestLogFilter extends ZuulFilter {

    @Value("${auth.service.http.endpoint.checkToken:http://localhost:7002/auth/checkToken}")
    private String checkTokenUrl;

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        ArrayList<String> cookies = new ArrayList<>();
        if (request.getCookies() != null &&  request.getCookies().length != 0) {
            for (Cookie c : request.getCookies()) { //нужно разобраться с сессией в authService (отправлять копию клиентского запроса, только с другим uri)
                cookies.add(String.format("%s=%s", c.getName(), c.getValue()));
            }
        }
        if (request.getRequestURI().contains("/p/") && request.getHeader(AUTHORIZATION) != null) { // change it to eureca and feign client
            final RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add(AUTHORIZATION, request.getHeader(AUTHORIZATION));
            headers.set("Cookie", String.join(";", cookies));
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            final String req = restTemplate.exchange(checkTokenUrl, HttpMethod.GET, entity, String.class).getBody();
            if (req == null || !req.equals("Token is correct")) {
                RequestContext.getCurrentContext().setSendZuulResponse(false);
            }
        }
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }
}
