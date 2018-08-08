package ar.com.javacuriosities.spring.mvc.dispatcher;

import ar.com.javacuriosities.spring.mvc.controller.AbstractController;
import ar.com.javacuriosities.spring.mvc.mapping.HandlerMapping;
import ar.com.javacuriosities.spring.mvc.model.ModelAndView;
import ar.com.javacuriosities.spring.mvc.view.ViewResolver;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AbstractController controller = HandlerMapping.getController(request, response);

            ModelAndView modelAndView = controller.handleRenderRequest(request, response);

            for (Map.Entry<String, Object> entry : modelAndView.getModel().entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }

            RequestDispatcher view = request.getRequestDispatcher(ViewResolver.getView(modelAndView));
            view.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DispatcherServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet Dispatcher";
    }
}