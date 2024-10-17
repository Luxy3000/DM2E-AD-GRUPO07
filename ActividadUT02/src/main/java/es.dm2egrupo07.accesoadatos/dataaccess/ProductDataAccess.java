package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Product;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el acceso a datos de productos.
 * Tiene métodos para realizar operaciones sobre sobre
 * productos, como contar, buscar, guardar y eliminar.
 */
public interface ProductDataAccess {

    /**
     * Cuenta el número total de productos.
     *
     * @return el número total de productos.
     */
    long count();

    /**
     * Verifica si existe un producto con la id dada.
     *
     * @param id el identificador del producto.
     * @return true si el producto existe, false en caso contrario.
     */
    boolean existsById(String id);

    /**
     * Busca un producto por su id.
     *
     * @param id el identificador del producto.
     * @return un Optional que contiene el producto si se encuentra,
     *         o vacío si no se encuentra.
     */
    Optional<Product> findById(String id);

    /**
     * Lista todos los productos.
     *
     * @return una lista con todos los productos.
     */
    List<Product> findAll();

    /**
     * Guarda un nuevo producto o actualiza uno existente.
     *
     * @param product el producto a guardar.
     * @return el producto guardado.
     */
    Product save(Product product);

    /**
     * Elimina un producto por su identificador.
     *
     * @param id la id del producto a eliminar.
     */
    void deleteById(String id);
}

