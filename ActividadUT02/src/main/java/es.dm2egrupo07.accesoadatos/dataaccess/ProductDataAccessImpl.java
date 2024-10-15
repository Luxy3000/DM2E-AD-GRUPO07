package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Product;

import java.util.List;

public class ProductDataAccessImpl implements ProductDataAccess {

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public List<Product> findByCode(String code) {
        return List.of();
    }

    @Override
    public List<Product> findByName(String name) {
        return List.of();
    }
}
