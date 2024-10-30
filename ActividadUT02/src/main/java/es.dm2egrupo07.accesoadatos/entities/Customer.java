package es.dm2egrupo07.accesoadatos.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Clase que representa a un cliente en la base de datos, incluyendo número de cliente, apellido, nombre, número de
 * teléfono, dirección, ciudad, estado, código postal, país, número de ventas que ha realizado y límite de crédito.
 */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
    /**
     * El número único del cliente.
     */
    @EqualsAndHashCode.Include
    private int customerNumber;

    /**
     * El nombre completo del cliente.
     */
    private String customerName;

    /**
     * El apellido del cliente.
     */
    private String contactLastName;

    /**
     * El nombre del cliente.
     */
    private String contactFirstName;

    /**
     * El número de teléfono del cliente.
     */
    private String phone;

    /**
     * La primera línea de la dirección del cliente.
     */
    private String addressLine1;

    /**
     * La segunda línea de la dirección del cliente, la mayoría vacías.
     */
    private String addressLine2;

    /**
     * El nombre de la ciudad donde vive el cliente.
     */
    private String city;

    /**
     * El nombre del estado donde vive el cliente.
     */
    private String state;

    /**
     * El código postal donde vive el cliente.
     */
    private String postalCode;

    /**
     * El nombre del país donde vive el cliente.
     */
    private String country;

    /**
     * El número de ventas que ha realizado el empleado.
     */
    private int salesRepEmployeeNumber;

    /**
     * La cantidad de límite de crédito que tiene el cliente.
     */
    private double creditLimit;
}
