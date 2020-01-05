package ar.com.javacurisioties.tags;


import ar.com.javacurisioties.tags.model.Element;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

public class ComboTagHandler extends SimpleTagSupport {

    private List<Element> elements;

    public ComboTagHandler() {
    }

    /**
     * Este método es llamado cuando el container invoca a este tag.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        try {
            // Podemos insertar código antes del body
            out.println("<strong>Titulo</strong>");
            out.println("<br/>");
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // Podemos insertar código después del body
            out.println("<select>");
            for (Element element : elements) {
                out.println("<option value = '" + element.getId() + "'>" + element.getName() + "</option>");
            }
            out.println("</select>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in NewTagHandler tag", ex);
        }
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
