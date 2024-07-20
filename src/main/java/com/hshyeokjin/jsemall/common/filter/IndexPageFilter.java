package com.hshyeokjin.jsemall.common.filter;

import com.hshyeokjin.jsemall.common.Resolver.ViewResolver;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "welcomePage", value = "index.do")}
)
public class IndexPageFilter extends HttpFilter {

    private String welcomePage;
    private ViewResolver viewResolver;
    @Override
    public void init() throws ServletException {
        viewResolver = new ViewResolver();
        welcomePage = getInitParameter("welcomePage");
        if (welcomePage == null || welcomePage.isEmpty()) {
            welcomePage = "index.do";
        }
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String requestURI = req.getRequestURI();
        if ("/".equals(requestURI)) {
            res.sendRedirect(req.getContextPath() + "/" + welcomePage);
        } else {
            chain.doFilter(req, res);
        }
    }
}
