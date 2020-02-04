package ar.com.javacurisioties.jaxws.faults.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "InvalidParameterFault")
public class InvalidParameter extends Exception {

    private InvalidParameterBean faultInfo;

    public InvalidParameter(String message, InvalidParameterBean faultInfo) {
        this(message, faultInfo, null);
    }

    public InvalidParameter(String message, InvalidParameterBean faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public InvalidParameterBean getFaultInfo() {
        return faultInfo;
    }
}