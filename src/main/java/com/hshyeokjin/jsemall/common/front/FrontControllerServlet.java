package com.hshyeokjin.jsemall.common.front;

import com.hshyeokjin.jsemall.common.Resolver.ViewResolver;
import com.hshyeokjin.jsemall.common.contoller.BaseController;
import com.hshyeokjin.jsemall.common.contoller.ControllerFactory;
import com.hshyeokjin.jsemall.common.util.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontServlet", urlPatterns = {"*.do"})
public class FrontControllerServlet extends HttpServlet {
    private ControllerFactory controllerFactory;
    private ViewResolver viewResolver;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        controllerFactory = (ControllerFactory) servletContext.getAttribute(ControllerFactory.CONTEXT_NAME);
        if (controllerFactory == null) {
            throw new ServletException("컨트롤러 팩토리 초기화 실패");
        }
        viewResolver = new ViewResolver();

        DbConnection.initialize();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            BaseController baseController = (BaseController) controllerFactory.getController(req);
            String viewName = baseController.execute(req, resp);

            if (viewResolver.isRedirect(viewName)) {
                String redirectUrl = viewResolver.getRedirectUrl(viewName);
                resp.sendRedirect(redirectUrl);
            } else {
                String layout = viewResolver.getLayOut(viewName);
                req.setAttribute(ViewResolver.LAYOUT_CONTENT_HOLDER, viewResolver.getPath(viewName));
                RequestDispatcher rd = req.getRequestDispatcher(layout);
                rd.include(req, resp);
            }
        } catch (Exception e) {
            DbConnection.setSqlError(true);
        } finally {
            DbConnection.reset();
        }
    }

    @Override
    public void destroy() {
        DbConnection.reset();
    }
}