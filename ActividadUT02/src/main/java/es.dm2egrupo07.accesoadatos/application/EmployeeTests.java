package es.dm2egrupo07.accesoadatos.application;

import es.dm2egrupo07.accesoadatos.dataaccess.EmployeeDataAccessImpl;
import es.dm2egrupo07.accesoadatos.entities.Employee;
import es.dm2egrupo07.accesoadatos.services.EmployeeService;
import es.dm2egrupo07.accesoadatos.services.EmployeeServiceImpl;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class EmployeeTests {
    private final EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDataAccessImpl());

//    /**
//     * Constructor que ejecuta todos los tests
//     *
//     */
//    public EmployeeTests() {
//        testSaveEmployee();
//        testFindAll();
//        testFindById();
//        testUpdateEmployee();
//        testDeleteEmployee();
//        testCount();
//    }

    /**
     * Test para probar la inserción de un nuevo empleado
     */
    public void testSaveEmployee() {
        Employee newEmployee = new Employee(1001, "Doe", "John","x1234", "john,doe@classicmodels.com", "1",1002, "Sales Rep");
        Employee savedEmployee = employeeService.save(newEmployee);

        if(savedEmployee != null && savedEmployee.getEmployeeNumber() == newEmployee.getEmployeeNumber()) {
            System.out.println("testSaveEmployee passed");
        } else {
            System.out.println("testSaveEmployee failed");
        }
    }

    /**
     * Test para probar la actualización de un empleado
     */
    public void testUpdateEmployee() {
        Employee updatedEmployee = new Employee(1001, "Doe", "Jane", "x1234", "jane.doe@classicmodels.com", "1", 1002, "Sales Manager");
        Employee result = employeeService.update(updatedEmployee);

        if(result != null && result.getEmployeeNumber() == updatedEmployee.getEmployeeNumber()) {
            System.out.println("testUpdateEmployee passed");
        } else {
            System.out.println("testUpdateEmployee failed");
        }
    }

    /**
     * Test para probar la eliminación de un empleado
     */
    public void testDeleteEmployee() {
        int employeeNumber = 1001;
        employeeService.deleteById(employeeNumber);

        if(!employeeService.existsById(employeeNumber)) {
            System.out.println("testDeleteEmployee passed");
        } else {
            System.out.println("testDeleteEmployee failed");
        }
    }

    /**
     * Test para probar la búsqueda de un empleado por su número id
     */
    public void testFindById() {
        int employeeNumber = 1001;
        Optional<Employee> employee = employeeService.findById(employeeNumber);

        if(employee.isPresent() && employee.get().getEmployeeNumber() == employeeNumber) {
            System.out.println("testFindById passed");
        } else {
            System.out.println("testFindById failed");
        }
    }

    /**
     * Test para probar la lista de todos los empleados
     */
    public void testFindAll() {
        List<Employee> employees = employeeService.findAll();

        if (!employees.isEmpty() && employees != null) {
            System.out.println("testFindAll passed");
        } else {
            System.out.println("testFindAll failed");
        }
    }

    /**
     * Test para probar el conteo de empleados
     */
    public void testCount() {
        long count = employeeService.count();

        if(count > 0) {
            System.out.println("testCount passed");
        } else {
            System.out.println("testCount failed");
        }
    }
}
