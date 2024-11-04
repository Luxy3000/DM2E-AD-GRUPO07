package es.dm2egrupo07.accesoadatos.application;

import es.dm2egrupo07.accesoadatos.dataaccess.OrderDataAccessImpl;
import es.dm2egrupo07.accesoadatos.dto.CreateOrderDto;
import es.dm2egrupo07.accesoadatos.entities.Order;
import es.dm2egrupo07.accesoadatos.services.OrderService;
import es.dm2egrupo07.accesoadatos.services.OrderServiceImpl;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class OrderTests {

    private static final OrderService orderService = new OrderServiceImpl(new OrderDataAccessImpl());

//    public OrderTests() {
//        testCreateOrder();
//        testCountOrders();
//        testFindById();
//        testFindAllOrders();
//        testUpdateOrder();
//        testDeleteOrder();
//        testExistsById();
//    }


    public void testCreateOrder(){
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
            e.getMessage();
        }
    }

    public void testFindById(){
        int orderId = 1001;
        Optional<Order> order = orderService.findById(orderId);
        if(order.isPresent()){
            System.out.println("testFindById passed");
        } else {
            System.out.println("testFindById failed");
        }
    }

    public void testFindAllOrders(){
        List<Order> orders = orderService.findAll();
        if(!orders.isEmpty()){
            System.out.println("testFindAllOrders passed");
        } else {
            System.out.println("testFindAllOrders failed");
        }
    }

    public void testUpdateOrder(){
        Order existingOrder = new Order(1001, new Date(), new Date(), null, "Shipped", "Updated status", 1);
        try {
            Order updatedOrder = orderService.update(existingOrder);
            System.out.println("testUpdateOrder passed");
        } catch (Exception e) {
            System.out.println("testUpdateOrder failed");
            e.getMessage();
        }
    }

    public void testDeleteOrder(){
        int orderId = 1001;
        try {
            orderService.deleteById(orderId);
            System.out.println("testDeleteOrder passed");
        } catch (Exception e) {
            System.out.println("testDeleteOrder failed");
            e.getMessage();
        }
    }

    public void testCountOrders(){
        try {
            long count = orderService.count();
            System.out.println("testCountOrders passed. Total: " + count);
        } catch (Exception e) {
            System.out.println("testCountOrders failed");
            e.getMessage();
        }

    }

    public void testExistsById(){
        int orderId = 1001;
        boolean exists = orderService.existsById(orderId);
        if(exists){
            System.out.println("testExistsById passed");
        } else {
            System.out.println("testExistsById failed");
        }
    }
}
