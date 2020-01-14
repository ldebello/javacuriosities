package ar.com.javacuriosities.jsf.converter.converters;

import ar.com.javacuriosities.jsf.converter.model.UrlData;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        StringBuilder url = new StringBuilder();

        if (!value.startsWith("http://")) {
            url.append("http://");
        }
        url.append(value);

        try {
            URI uri = new URI(url.toString());
        } catch (URISyntaxException e) {
            FacesMessage msg = new FacesMessage("Error convirtiendo URL", "Formato invalido");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        }

        UrlData urlData = new UrlData(url.toString());
        return urlData;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        UrlData urlData = (UrlData) value;
        return urlData.getUrl();
    }
}
