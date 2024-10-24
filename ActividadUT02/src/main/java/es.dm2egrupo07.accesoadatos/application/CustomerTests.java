package es.dm2egrupo07.accesoadatos.application;

import es.dm2egrupo07.accesoadatos.entities.Customer;
import es.dm2egrupo07.accesoadatos.services.CustomerService;

import java.util.List;

/**
 * Clase para probar que los métodos realizados en las otras clases funcionan correctamente antes de implementarlos en
 * el programa principal.
 */
public class CustomerTests {

    private final CustomerService customerService;

    public CustomerTests(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Método para acceder a la interfaz CustomerService y probar su método para contar el múnero de tablas de Customer.
     */
    public void count() {
        long count = customerService.count();

        if (count > 0) {
            System.out.println("Tablas contadas correctamente.");
        } else {
            System.out.println("Tablas no contadas correctamente.");
        }
    }

    /**
     * Método para acceder a la interfaz CustomerService y probar su método para guardar un Customer.
     */
    public void save() {
        Customer customer1 = new Customer(500, "Vera Sanz", "Sanz",
                "Vera", "611788944", "address1", "address2", "SSR",
                "Madrid", "20875", "Spain", 1717, 324278.00);
        Customer savedCustomer = customerService.save(customer1);

        if (savedCustomer != null && savedCustomer.getCustomerNumber() == customer1.getCustomerNumber()) {
            System.out.println("El Customer se ha guardado correctamente.");
        } else {
            System.out.println("El Customer no se ha guardado.");
        }
    }

    /**
     * Método para acceder a la interfaz CustomerService y probar su método para actualizar un Customer.
     */
    public void update() {
        Customer customer2 = new Customer(500, "Vera Sanz", "Sanz",
                "Vera", "611788944", "address11", "address22", "SSR",
                "Madrid", "28750", "Spain", 1717, 324278.00);
        Customer updateCustomer = customerService.update(customer2);

        if (updateCustomer != null && "Vera Sanz".equals(updateCustomer.getCustomerName())) {
            System.out.println("El Customer se ha actualizado correctamente.");
        } else {
            System.out.println("El Customer no se ha actualizado.");
        }
    }

    /**
     * Método para acceder a la interfaz CustomerService y probar su método para buscar un Customer por su ID.
     */
    public void findById() {
        int id = 500;
        customerService.findById(id);
        System.out.println("Customer encontrado.");
    }

    /**
     * Método para acceder a la interfaz CustomerService y probar su método para comprobar si existe un Customer por su
     * ID.
     */
    public void existsById() {
        int id = 500;

        if (customerService.existsById(id)) {
            System.out.println("El customer se ha encontrado.");
        } else {
            System.out.println("El customer no se ha encontrado.");
        }
    }

    /**
     * Método para acceder a la interfaz CustomerService y probar su método para buscar todos los Customer.
     */
    public void findAll() {
        List<Customer> customers = customerService.findAll();

        if (!customers.isEmpty()) {
            System.out.println("Lista encontrada correctamente.\n");
            System.out.println("Lista de customers: " + customers.size());
        } else {
            System.out.println("Lista no encontrada.");
        }
    }

    /**
     * Método para acceder a la interfaz CustomerService y probar su método para borrar un Customer por su ID.
     */
    public void deleteById() {
        int id = 500;

        if (customerService.existsById(id)) {
            customerService.deleteById(id);
            System.out.println("Customer eliminado con éxito.");
        } else {
            System.out.println("El customer no se ha encontrado para poder borrarlo.");
        }
    }

}
