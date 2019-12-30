package ar.com.javacuriosities.ajax;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ComboBoxServlet extends HttpServlet {

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
                out.print("<OPTION value='1'>Categoria 1</OPTION>");
                out.print("<OPTION value='2'>Categoria 2</OPTION>");
                out.print("<OPTION value='3'>Categoria 3</OPTION>");
                out.print("<OPTION value='4'>Categoria 4</OPTION>");
                out.print("<OPTION value='5'>Categoria 5</OPTION>");
            } else if ("reload".equalsIgnoreCase(action)) {
                String category = request.getParameter("categoria");
                switch (category) {
                    case "1":
                        out.print("<OPTION value='1'>Subcategoria H</OPTION>");
                        out.print("<OPTION value='2'>Subcategoria T</OPTION>");
                        out.print("<OPTION value='3'>Subcategoria D</OPTION>");
                        break;
                    case "2":
                        out.print("<OPTION value='4'>Subcategoria Z</OPTION>");
                        out.print("<OPTION value='5'>Subcategoria G</OPTION>");
                        out.print("<OPTION value='6'>Subcategoria R</OPTION>");
                        break;
                    case "3":
                        out.print("<OPTION value='7'>Subcategoria A</OPTION>");
                        out.print("<OPTION value='8'>Subcategoria V</OPTION>");
                        out.print("<OPTION value='9'>Subcategoria X</OPTION>");
                        break;
                    case "4":
                        out.print("<OPTION value='10'>Subcategoria U</OPTION>");
                        out.print("<OPTION value='11'>Subcategoria J</OPTION>");
                        out.print("<OPTION value='12'>Subcategoria E</OPTION>");
                        break;
                    case "5":
                        out.print("<OPTION value='13'>Subcategoria Y</OPTION>");
                        out.print("<OPTION value='14'>Subcategoria W</OPTION>");
                        out.print("<OPTION value='15'>Subcategoria N</OPTION>");
                        break;
                }
            }
        }
    }
}
