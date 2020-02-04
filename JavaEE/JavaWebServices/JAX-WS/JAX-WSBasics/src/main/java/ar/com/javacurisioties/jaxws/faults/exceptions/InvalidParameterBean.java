package ar.com.javacurisioties.jaxws.faults.exceptions;

public class InvalidParameterBean {

    private String parameterName;
    private Integer parameterCode;
    private Integer version;

    public InvalidParameterBean() {
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Integer getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(Integer parameterCode) {
        this.parameterCode = parameterCode;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
