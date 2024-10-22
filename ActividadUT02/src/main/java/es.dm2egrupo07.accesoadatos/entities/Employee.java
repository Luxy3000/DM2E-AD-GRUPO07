package es.dm2egrupo07.accesoadatos.entities;

import lombok.*;

/**
* Esta clase representa a un empleado en la base de datos, incluyendo
* número de empleado, apellido, nombre, su extensión, su email, el código
* de su oficina, el identificador de su superior y el título de su trabajo
*/

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {
    /**
     * El número único del empleado
     * */
    @EqualsAndHashCode.Include
    private int employeeNumber;
    /**
     * Apellido del empleado
     * */
    private String lastName;

    /**
     * Nombre del empleado
     * */
    private String firstName;

    /**
     * Extensión del empleado
     * */
    private String extension;
    /**
     * Correo electrónico del empleado
     * */
    private String email;

    /**
     * Código de la oficina del empleado
     * */
    private String officeCode;

    /**
     * Código del superior del empleado
     * */
    private int reportsTo;

    /**
     * Título del puesto del empleado
     * */
    private String jobTitle;
}
