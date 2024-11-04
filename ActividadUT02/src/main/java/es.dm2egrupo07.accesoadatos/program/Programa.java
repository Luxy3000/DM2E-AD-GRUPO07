package es.dm2egrupo07.accesoadatos.program;

import es.dm2egrupo07.accesoadatos.application.CustomerTests;
import es.dm2egrupo07.accesoadatos.application.EmployeeTests;
import es.dm2egrupo07.accesoadatos.application.OrderTests;
import es.dm2egrupo07.accesoadatos.application.ProductTests;
import es.dm2egrupo07.accesoadatos.dataaccess.OrderDataAccessImpl;
import es.dm2egrupo07.accesoadatos.services.CustomerService;
import es.dm2egrupo07.accesoadatos.services.EmployeeService;
import es.dm2egrupo07.accesoadatos.services.OrderService;
import es.dm2egrupo07.accesoadatos.services.OrderServiceImpl;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        //new EmployeeTests();
        //new OrderTests();
        //new CustomerTests();
        //new ProductTests();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Seleccione la tabla para realizar pruebas:");
            System.out.println("1. Empleados");
            System.out.println("2. Productos");
            System.out.println("3. Pedidos");
            System.out.println("4. Clientes");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    runEmployeeTests();
                    break;
                case 2:
                    runProductTests();
                    break;
                case 3:
                    runOrderTests();
                    break;
                case 4:
                    runCustomerTests();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }

        System.out.println("Programa terminado.");
        scanner.close();
    }

    private static void runEmployeeTests() {
        EmployeeTests employeeTests = new EmployeeTests();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nSeleccione la prueba de empleados:");
            System.out.println("1. Guardar empleado");
            System.out.println("2. Actualizar empleado");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Buscar empleado por ID");
            System.out.println("5. Listar todos los empleados");
            System.out.println("6. Contar empleados");
            System.out.println("7. Volver");
            System.out.print("Ingrese su opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    employeeTests.testSaveEmployee();
                    break;
                case 2:
                    employeeTests.testUpdateEmployee();
                    break;
                case 3:
                    employeeTests.testDeleteEmployee();
                    break;
                case 4:
                    employeeTests.testFindById();
                    break;
                case 5:
                    employeeTests.testFindAll();
                    break;
                case 6:
                    employeeTests.testCount();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }

    private static void runProductTests() {
        ProductTests productTests = new ProductTests();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nSeleccione la prueba de productos:");
            System.out.println("1. Guardar producto");
            System.out.println("2. Actualizar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Buscar producto por ID");
            System.out.println("5. Listar todos los productos");
            System.out.println("6. Comprobar existencia de producto por ID");
            System.out.println("7. Volver");
            System.out.print("Ingrese su opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    productTests.testSave();
                    break;
                case 2:
                    productTests.testUpdate();
                    break;
                case 3:
                    productTests.testDelete();
                    break;
                case 4:
                    productTests.testFindById();
                    break;
                case 5:
                    productTests.testFindAll();
                    break;
                case 6:
                    productTests.testExistsById();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }

    private static void runOrderTests() {
        OrderTests orderTests = new OrderTests();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nSeleccione la prueba de pedidos:");
            System.out.println("1. Crear pedido");
            System.out.println("2. Actualizar pedido");
            System.out.println("3. Eliminar pedido");
            System.out.println("4. Buscar pedido por ID");
            System.out.println("5. Listar todos los pedidos");
            System.out.println("6. Contar pedidos");
            System.out.println("7. Comprobar existencia de pedido por ID");
            System.out.println("8. Volver");
            System.out.print("Ingrese su opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    orderTests.testCreateOrder();
                    break;
                case 2:
                    orderTests.testUpdateOrder();
                    break;
                case 3:
                    orderTests.testDeleteOrder();
                    break;
                case 4:
                    orderTests.testFindById();
                    break;
                case 5:
                    orderTests.testFindAllOrders();
                    break;
                case 6:
                    orderTests.testCountOrders();
                    break;
                case 7:
                    orderTests.testExistsById();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }

    private static void runCustomerTests() {
        CustomerTests customerTests = new CustomerTests();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nSeleccione la prueba de clientes:");
            System.out.println("1. Guardar cliente");
            System.out.println("2. Actualizar cliente");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Buscar cliente por ID");
            System.out.println("5. Listar todos los clientes");
            System.out.println("6. Contar clientes");
            System.out.println("7. Comprobar existencia de cliente por ID");
            System.out.println("8. Volver");
            System.out.print("Ingrese su opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    customerTests.save();
                    break;
                case 2:
                    customerTests.update();
                    break;
                case 3:
                    customerTests.deleteById();
                    break;
                case 4:
                    customerTests.findById();
                    break;
                case 5:
                    customerTests.findAll();
                    break;
                case 6:
                    customerTests.count();
                    break;
                case 7:
                    customerTests.existsById();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }
}
