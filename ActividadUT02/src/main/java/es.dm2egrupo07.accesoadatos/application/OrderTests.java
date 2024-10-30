package es.dm2egrupo07.accesoadatos.application;

import es.dm2egrupo07.accesoadatos.dto.CreateOrderDto;
import es.dm2egrupo07.accesoadatos.services.OrderService;

import java.util.Date;

public class OrderTests {

    private OrderService orderService;

    public OrderTests(OrderService orderService) {
        this.orderService = orderService;
    }

    private void testCreateOrder(){
        CreateOrderDto orderDto = new CreateOrderDto();
        orderDto.setOrderNumber(1001);
        orderDto.setOrderDate(new Date());
        orderDto.setRequiredDate(new Date());
        orderDto.setStatus("Pending");
        orderDto.setComments("Test order");
        orderDto.setCustomerNumber(1);

        try {
            orderService.create(orderDto);
            System.out.println("testCreateOrder passed");
        } catch (Exception e) {
            System.out.println("testCreateOrder failed");
        }
    }
}
