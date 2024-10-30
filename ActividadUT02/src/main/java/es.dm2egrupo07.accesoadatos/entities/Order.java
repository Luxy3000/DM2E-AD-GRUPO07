package es.dm2egrupo07.accesoadatos.entities;

import lombok.*;

import java.util.Date;

/**
 * Esta clase representa un pedido en la base de datos, incluyendo el número
 * de pedido, fechas relevantes, estado, comentarios y número de cliente.
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    /**
     * El número único del pedido.
     */
    @EqualsAndHashCode.Include
    private final int orderNumber;

    /**
     * La fecha en que se realizó el pedido.
     */
    private final Date orderDate;

    /**
     * La fecha requerida para la entrega del pedido.
     */
    private final Date requiredDate;

    /**
     * La fecha en que se envió el pedido.
     */
    private final Date shippedDate;

    /**
     * El estado actual del pedido.
     */
    private final String status;

    /**
     * Comentarios adicionales sobre el pedido.
     */
    private final String comments;

    /**
     * El número del cliente que realizó el pedido.
     */
    private final int customerNumber;
}
