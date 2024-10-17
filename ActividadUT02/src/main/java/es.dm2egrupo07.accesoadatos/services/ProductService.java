package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.entities.Product;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios de los productos.
 * Tiene métodos para realizar operaciones básicas como buscar,
 * contar, guardar y eliminar productos.
 */
public interface ProductService {

    /**
     * Recupera todos los productos.
     *
     * @return una lista de productos.
     */
    List<Product> findAll();

    /**
     * Cuenta el número total de productos.
     *
     * @return el número total de productos.
     */
    long countProducts();

    /**
     * Busca un producto por su código.
     *
     * @param productCode el código del producto a buscar.
     * @return un Optional que contiene el producto si se encuentra,
     *         o vacío si no se encuentra.
     */
    Optional<Product> findById(String productCode);

    /**
     * Verifica si existe un producto con el código dado.
     *
     * @param productCode el código del producto a verificar.
     * @return true si el producto existe; false en caso contrario.
     */
    boolean existsById(String productCode);

    /**
     * Guarda un nuevo producto o actualiza uno existente.
     *
     * @param product el producto a guardar.
     * @return el producto guardado.
     */
    Product saveProduct(Product product);

    /**
     * Elimina un producto por su código.
     *
     * @param productCode el código del producto a eliminar.
     */
    void deleteById(String productCode);
}

