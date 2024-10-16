package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Employee;
import java.util.List;
import java.util.Optional;

//Definir las acciones que se pueden realizar sobre la entidad Employee
//Es una plantilla predefinida
public interface EmployeeDataAccess {

    //Encontrar empleado por el número de empleado,
    //usa numero del empleado como parametro y devuelve obejeto.
    //Employee findById(int employeeNumber); --> Eliminado porque puede dar NullPointerException si no llega a encontrar al employee
    Optional<Employee> findEmployee(int employeeNumber);

    //Obtener lista de todos los empreados
    //devuelve lista de objetos Employee
    List<Employee> findAll();

    //Acción 1, guardar nuevo empleado en la BBDD
    //Recibe objeto Employee y devuelve true si funciona
    Employee save(Employee employee);

    //Acción 2, actualiar info de un empleado existente
    //Recibe objeto employee y devuelve true si funciona
    Employee update(Employee employee);

    //Acción 3, eliminar un empleado de la BBDD
    //Recibe numero de empleado y devuelve true si funciona
    void deleteById(int employeeNumber);

    //Accion 4, dar la longitud de la tabla de empleados
    long count();

    //Accion 5, verificar si existe un empleado con un id
    boolean existById(int employeeNumber);

}
