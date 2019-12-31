package ar.com.javacuriosities.ajax;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DropdownServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");

            if ("start".equalsIgnoreCase(action)) {
                out.print("<option value='1'>Categoria 1</option>");
                out.print("<option value='2'>Categoria 2</option>");
                out.print("<option value='3'>Categoria 3</option>");
                out.print("<option value='4'>Categoria 4</option>");
                out.print("<option value='5'>Categoria 5</option>");
            } else if ("reload".equalsIgnoreCase(action)) {
                String category = request.getParameter("category");
                switch (category) {
                    case "1":
                        out.print("<option value='1'>Subcategoria H</option>");
                        out.print("<option value='2'>Subcategoria T</option>");
                        out.print("<option value='3'>Subcategoria D</option>");
                        break;
                    case "2":
                        out.print("<option value='4'>Subcategoria Z</option>");
                        out.print("<option value='5'>Subcategoria G</option>");
                        out.print("<option value='6'>Subcategoria R</option>");
                        break;
                    case "3":
                        out.print("<option value='7'>Subcategoria A</option>");
                        out.print("<option value='8'>Subcategoria V</option>");
                        out.print("<option value='9'>Subcategoria X</option>");
                        break;
                    case "4":
                        out.print("<option value='10'>Subcategoria U</option>");
                        out.print("<option value='11'>Subcategoria J</option>");
                        out.print("<option value='12'>Subcategoria E</option>");
                        break;
                    case "5":
                        out.print("<option value='13'>Subcategoria Y</option>");
                        out.print("<option value='14'>Subcategoria W</option>");
                        out.print("<option value='15'>Subcategoria N</option>");
                        break;
                }
            }
        }
    }
}
