package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Product;
import java.util.List;

public interface ProductDataAccess {
    List<Product> findAll();
    List<Product> findByCode(String code);
    List<Product> findByName(String name);
}
