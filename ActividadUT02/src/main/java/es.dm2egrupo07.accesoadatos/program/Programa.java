package es.dm2egrupo07.accesoadatos.program;

import es.dm2egrupo07.accesoadatos.application.CustomerTests;
import es.dm2egrupo07.accesoadatos.application.EmployeeTests;
import es.dm2egrupo07.accesoadatos.application.OrderTests;
import es.dm2egrupo07.accesoadatos.dataaccess.OrderDataAccessImpl;
import es.dm2egrupo07.accesoadatos.services.CustomerService;
import es.dm2egrupo07.accesoadatos.services.EmployeeService;
import es.dm2egrupo07.accesoadatos.services.OrderService;
import es.dm2egrupo07.accesoadatos.services.OrderServiceImpl;

public class Programa {

    public static void main(String[] args) {

        //new EmployeeTests();
        //new OrderTests();
        new CustomerTests();
    }
}
