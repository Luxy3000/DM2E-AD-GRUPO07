package es.dm2egrupo07.accesoadatos.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {
    @EqualsAndHashCode.Include
    private final int orderNumber;
    private final Date orderDate;
    private final Date requiredDate;
    private final Date shippedDate;
    private final String status;
    private final String comments;
    private final int customerNumber;
}
