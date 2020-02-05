package ar.com.javacurisioties.jaxws.schema_validation;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "consultar", namespace = "http://validation.message.javacuriosities.com.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultar", namespace = "http://validation.message.javacuriosities.com.ar/", propOrder = {
        "code",
        "start",
        "end"
})
public class Parameters {

    @XmlElement(name = "code", required = true)
    private String code;
    @XmlElement(name = "start")
    private int start;
    @XmlElement(name = "end")
    private int end;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
