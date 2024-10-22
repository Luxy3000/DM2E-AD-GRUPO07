package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Employee;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el acceso a datos de empleados.
 * Tiene métodos para realizar operaciones sobre los empleados como
 * buscar por id del empleado, dar una lista de todos los empleados,
 * agregar, actualizar, borrar, contar o comprobar si existe.
 * */
//Definir las acciones que se pueden realizar sobre la entidad Employee
//Es una plantilla predefinida
public interface EmployeeDataAccess {

    /**
     * Busca un empleado con la id dada
     *
     * @param employeeNumber número identificador del empleado
     * @return un Optional que contiene el empleado si se encuentra,
     *         o vacío si no se encuentra.
     * */
    //Encontrar empleado por el número de empleado,
    //usa numero del empleado como parametro y devuelve obejeto.
    //Employee findById(int employeeNumber); --> Eliminado porque puede dar NullPointerException si no llega a encontrar al employee
    Optional<Employee> findById(int employeeNumber);

    /**
     * Lista de todos los empleados
     *
     * @return una lista con todos los empleados
     * */
    //Obtener lista de todos los empreados
    //devuelve lista de objetos Employee
    List<Employee> findAll();


    /**
     * Agrega un nuevo empleado o actualiza uno existente.
     *
     * @param employee objeto Empleado
     * @return el empleado guardado
     * */
    //Acción 1, guardar nuevo empleado en la BBDD
    //Recibe objeto Employee y devuelve true si funciona
    Employee save(Employee employee);

    /**
     * Actualiza un empleado existente
     *
     * @param employee empleado a actualizar
     * @return empleado actualizado
     * */
    //Acción 2, actualiar info de un empleado existente
    //Recibe objeto employee y devuelve true si funciona
    Employee update(Employee employee);

    /**
     * Elimina un empleado de la BBDD
     *
     * @param employeeNumber número de empleado
     * */
    //Acción 3, eliminar un empleado de la BBDD
    //Recibe numero de empleado y devuelve true si funciona
    void deleteById(int employeeNumber);

    /**
     * Cuenta el número total de empleados
     *
     * @return número total de empleados en la BBDD
     * */
    //Accion 4, dar la longitud de la tabla de empleados
    long count();

    /**
     * Verifica si existe un porducto con la id dada
     *
     * @param employeeNumber número identificador de empleado
     * @return true si existe, false si no existe.
     * */
    //Accion 5, verificar si existe un empleado con un id
    boolean existById(int employeeNumber);

}
