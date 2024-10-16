package es.dm2egrupo07.accesoadatos.entities;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {
    @EqualsAndHashCode.Include
    private int employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String officeCode;
    private int reportsTo;
    private String jobTitle;
}
