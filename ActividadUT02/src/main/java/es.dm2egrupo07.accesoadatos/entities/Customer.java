package es.dm2egrupo07.accesoadatos.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
  @EqualsAndHashCode.Include
    private int customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private int salesRepEmployeeNumber;
    private double creditLimit;
}
