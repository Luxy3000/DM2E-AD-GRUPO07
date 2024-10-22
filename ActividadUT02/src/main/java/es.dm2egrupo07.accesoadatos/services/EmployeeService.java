package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.entities.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios de los empleados.
 * Tiene métodos para realizar operaciones básicas como buscar, guardar,
 * actualizar, borrar, contar o comprobar si existe un empleado.
 * */
//Interfaz para los métodos para trabajar con los empleados en la capa de servicios
public interface EmployeeService {

    /**
     * Busca un empleado por su número identificador
     *
     * @param employeeNumber número identificador de empleado
     * @return Optional que contiene el empleado o vacío si no se encuentra
     */
    Optional<Employee> findById(int employeeNumber);

    /**
     * Lista de todos los empleados en la BBDD
     *
     * @return lista con todos los empleados
     */
    List<Employee> findAll();

    /**
     * Guardar un nuevo empleado a la BBDD
     *
     * @param employee emplado a guardar
     * @return empleado guardado
     */
    Employee save(Employee employee);

    /**
     * Actualizar un empleado de la BBDD
     *
     * @param employee empleado a actualizar
     * @return empleado actualizado
     */
    Employee update(Employee employee);

    /**
     * Eliminar un empleado de la BBDD
     *
     * @param employeeNumber número id del empleado a borrar
     */
    void deleteById(int employeeNumber);

    /**
     * Contar cuántos empleados hay en la BBDD
     *
     * @return número de mepleados en la BBDD
     */
    long count();

    /**
     * Comprobar si un empleado existe en la BBDD
     * @param employeeNumber número id del empleado
     * @return true si el empleado existe, false si no existe.
     */
    boolean existsById(int employeeNumber);

}
