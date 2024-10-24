package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.dataaccess.CustomerDataAccess;
import es.dm2egrupo07.accesoadatos.entities.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDataAccess customerDataAccess;

    public CustomerServiceImpl(CustomerDataAccess customerDataAccess) {
        this.customerDataAccess = customerDataAccess;
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para contar las
     * columnas de la tabla Customer.
     * @return número de columnas totales de la tabla
     */
    @Override
    public long count() {
        return customerDataAccess.count();
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para comprobar
     * si existe un Customer por su ID.
     * @param id
     * @return true si existe y false si no existe
     */
    @Override
    public boolean existsById(int id) {
        return customerDataAccess.existsById(id);
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para buscar un
     * Customer por su ID.
     * @param id
     * @return el Customer correspondiente al ID dado
     */
    @Override
    public Optional<Customer> findById(int id) {
        return customerDataAccess.findById(id);
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para buscar
     * todos los Customer.
     * @return una lista con todos los Customer
     */
    @Override
    public List<Customer> findAll() {
        return customerDataAccess.findAll();
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para guardar un
     * Customer nuevo.
     * @param customer
     * @return el Customer guardado
     */
    @Override
    public Customer save(Customer customer) {
        return customerDataAccess.save(customer);
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para actualizar
     * un Customer.
     * @param customer
     * @return el Customer actualizado
     */
    @Override
    public Customer update(Customer customer) {
        return customerDataAccess.update(customer);
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para eliminar un
     * Customer por su ID.
     * @param id
     */
    @Override
    public void deleteById(int id) {
        customerDataAccess.deleteById(id);
    }
}
