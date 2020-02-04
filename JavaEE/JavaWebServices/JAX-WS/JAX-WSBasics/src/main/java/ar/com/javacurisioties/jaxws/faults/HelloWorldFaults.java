package ar.com.javacurisioties.jaxws.faults;

import ar.com.javacurisioties.jaxws.faults.exceptions.InvalidParameter;
import ar.com.javacurisioties.jaxws.faults.exceptions.InvalidParameterBean;
import ar.com.javacurisioties.jaxws.faults.exceptions.MissingParameter;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class HelloWorldFaults {

    @WebMethod(operationName = "checkUser")
    public String checkUser(@WebParam(name = "userName") String userName) throws MissingParameter {
        if (userName == null || "".equals(userName)) {
            throw new MissingParameter("The parameter userName is missing");
        }
        return "Hello " + userName + " !";
    }

    @WebMethod(operationName = "validateUser")
    public String validateUser(@WebParam(name = "userName") String userName) throws InvalidParameter {
        if (userName == null || "".equals(userName)) {
            InvalidParameterBean bean = new InvalidParameterBean();
            bean.setParameterCode(0001);
            bean.setParameterName("UserName");
            bean.setVersion(1);
            throw new InvalidParameter("The parameter userName is invalid", bean);
        }
        return "Hello " + userName + " !";
    }
}
