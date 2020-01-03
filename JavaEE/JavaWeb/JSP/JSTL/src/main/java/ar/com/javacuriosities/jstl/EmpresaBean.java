package ar.com.javacuriosities.jstl;

import java.util.ArrayList;
import java.util.List;

public class EmpresaBean {

    public EmpresaBean() {
    }

    public List<Empleado> getEmpleados() {
        List<Empleado> empleados = new ArrayList();
        empleados.add(new Empleado("Juan", "Perez", "5.845.269"));
        empleados.add(new Empleado("Ernesto", "Martin", "2.598.229"));
        empleados.add(new Empleado("Mauro", "Reyes", "6.248.962"));
        empleados.add(new Empleado("Cecilia", "Perez", "2.585.213"));
        empleados.add(new Empleado("Maria", "Polis", "6.854.285"));
        return empleados;
    }
}
