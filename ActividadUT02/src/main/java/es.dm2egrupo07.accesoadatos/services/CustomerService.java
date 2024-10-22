package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
  Long count();
    Boolean existsById(int id);
    Optional<Customer> findById(int id);
    List<Customer> findAll();
    Customer save(Customer customer);
    void deleteById(int id);
}
