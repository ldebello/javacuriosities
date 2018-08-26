package ar.com.javacuriosities.spring.mvc.dispatcher;

import ar.com.javacuriosities.spring.mvc.controller.AbstractController;
import ar.com.javacuriosities.spring.mvc.mapping.HandlerMapping;
import ar.com.javacuriosities.spring.mvc.model.ModelAndView;
import ar.com.javacuriosities.spring.mvc.view.ViewResolver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DispatcherServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("Step 1 - Handling Request");

            System.out.println("Step 2 - Delegating to Handler Mapping");
            AbstractController controller = HandlerMapping.getController(request, response);

            System.out.println("Step 3 - Executing to Controller");
            ModelAndView modelAndView = controller.handleRenderRequest(request, response);

            for (Map.Entry<String, Object> entry : modelAndView.getModel().entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }

            System.out.println("Step 4 - Resolving view");
            String view = ViewResolver.getView(modelAndView);

            System.out.println("Step 5 - Response");
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
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