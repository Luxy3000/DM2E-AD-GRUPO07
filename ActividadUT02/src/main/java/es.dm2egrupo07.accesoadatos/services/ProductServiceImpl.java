package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.dataaccess.ProductDataAccess;
import es.dm2egrupo07.accesoadatos.entities.Product;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz ProductService.
 * Proporciona los métodos para gestionar productos utilizando
 * el acceso a datos proporcionado por ProductDataAccess.
 */
public class ProductServiceImpl implements ProductService {

    private final ProductDataAccess productDataAccess;

    /**
     * Constructor que inicializa el ProductDataAccess.
     *
     * @param productDataAccess el acceso a datos de productos a utilizar.
     */
    public ProductServiceImpl(ProductDataAccess productDataAccess) {
        this.productDataAccess = productDataAccess;
    }

    /**
     * Recupera todos los productos.
     *
     * @return una lista de productos.
     */
    @Override
    public List<Product> findAll() {
        return productDataAccess.findAll();
    }

    /**
     * Cuenta el número total de productos.
     *
     * @return el número total de productos.
     */
    @Override
    public long countProducts() {
        return productDataAccess.count();
    }

    /**
     * Busca un producto por su código.
     *
     * @param productCode el código del producto a buscar.
     * @return un Optional que contiene el producto si se encuentra,
     *         o vacío si no se encuentra.
     */
    @Override
    public Optional<Product> findById(String productCode) {
        return productDataAccess.findById(productCode);
    }

    /**
     * Verifica si existe un producto con el código dado.
     *
     * @param productCode el código del producto a verificar.
     * @return true si el producto existe; false en caso contrario.
     */
    @Override
    public boolean existsById(String productCode) {
        return productDataAccess.existsById(productCode);
    }

    /**
     * Guarda un nuevo producto o actualiza uno existente.
     *
     * @param product el producto a guardar.
     * @return el producto guardado.
     */
    @Override
    public Product saveProduct(Product product) {
        return productDataAccess.save(product);
    }

    /**
     * Elimina un producto por su código.
     *
     * @param productCode el código del producto a eliminar.
     */
    @Override
    public void deleteById(String productCode) {
        productDataAccess.deleteById(productCode);
    }
}

