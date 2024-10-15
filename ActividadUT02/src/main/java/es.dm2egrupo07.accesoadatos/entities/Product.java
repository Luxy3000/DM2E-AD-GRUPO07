package es.dm2egrupo07.accesoadatos.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @EqualsAndHashCode.Include
    private final String productCode;
    private final String productName;
    private final String productLine;
    private final String productScale;
    private final String productVendor;
    private final String productDescription;
    private final int quantityInStock;
    private final double buyPrice;
    private final double msrp;

}
