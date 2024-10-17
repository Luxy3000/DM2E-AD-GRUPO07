package es.dm2egrupo07.accesoadatos.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Esta clase representa un producto en la base de datos,
 * incluyendo su código, nombre, línea, escala, proveedor, descripción,
 * cantidad en stock, precio de compra y precio de venta sugerido.
 */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    /**
     * El código único del producto.
     */
    @EqualsAndHashCode.Include
    private final String productCode;

    /**
     * El nombre del producto.
     */
    private final String productName;

    /**
     * La línea a la que pertenece el producto.
     */
    private final String productLine;

    /**
     * La escala del producto, que puede indicar su tamaño o tamaño relativo.
     */
    private final String productScale;

    /**
     * El proveedor del producto.
     */
    private final String productVendor;

    /**
     * Una descripción detallada del producto.
     */
    private final String productDescription;

    /**
     * La cantidad de producto disponible en stock.
     */
    private final int quantityInStock;

    /**
     * El precio de compra del producto.
     */
    private final double buyPrice;

    /**
     * El precio de venta sugerido al público (MSRP).
     */
    private final double msrp;
}
