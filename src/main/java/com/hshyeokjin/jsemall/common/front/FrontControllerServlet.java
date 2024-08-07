package com.hshyeokjin.jsemall.common.front;

import com.hshyeokjin.jsemall.common.Resolver.ViewResolver;
import com.hshyeokjin.jsemall.common.controller.BaseController;
import com.hshyeokjin.jsemall.common.controller.ControllerFactory;
import com.hshyeokjin.jsemall.common.util.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontControllerServlet", urlPatterns = {"*.do"})
public class FrontControllerServlet extends HttpServlet {
    private ControllerFactory controllerFactory;
    private ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {

        ServletConfig config = getServletConfig();
        ServletContext servletContext = config.getServletContext();
        controllerFactory = (ControllerFactory) servletContext.getAttribute(ControllerFactory.CONTEXT_NAME);
        if (controllerFactory == null) {
            throw new ServletException("컨트롤러 팩토리 초기화 실패");
        }
        viewResolver = new ViewResolver();

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        DbConnection.initialize();

        try {
            BaseController baseController = (BaseController) controllerFactory.getController(request);
            String viewName = baseController.execute(request, response);
            if (viewResolver.isRedirect(viewName)) {
                String redirectUrl = viewResolver.getRedirectUrl(viewName);
                response.sendRedirect(redirectUrl);
            } else {
                String layout = viewResolver.getLayOut(viewName);
                request.setAttribute(ViewResolver.LAYOUT_CONTENT_HOLDER, viewResolver.getPath(viewName));
                RequestDispatcher rd = request.getRequestDispatcher(layout);
                rd.include(request, response);
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
