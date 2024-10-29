package es.dm2egrupo07.accesoadatos.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {
    private int orderNumber;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private String status;
    private String comments;
    private int customerNumber;
    private List<OrderDetailDto> orderDetails;

    @Getter
    @Setter
    public static class OrderDetailDto{
        private String productCode;
        private int quantityOrdered;
        private double priceEach;
        private int orderLineNumber;
    }
}
