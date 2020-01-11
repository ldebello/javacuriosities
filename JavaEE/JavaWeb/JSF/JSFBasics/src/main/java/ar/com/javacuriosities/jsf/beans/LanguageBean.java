package ar.com.javacuriosities.jsf.beans;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Named("language")
@ViewScoped
public class LanguageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Map<String, Object> idiomas = new LinkedHashMap<>();

    static {
        idiomas.put("Español", new Locale("es", "AR"));
        idiomas.put("Frances", Locale.FRENCH);
        idiomas.put("Ingles", Locale.ENGLISH);
    }

    private String localeCode;

    public LanguageBean() {
        System.out.println("Construyendo LanguageBean");
        this.localeCode = "es";
    }

    public Map<String, Object> getIdiomas() {
        return idiomas;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public void countryLocaleCodeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();

        for (Map.Entry<String, Object> entry : idiomas.entrySet()) {

            if (entry.getValue().toString().equals(newLocaleValue)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
    }
}
