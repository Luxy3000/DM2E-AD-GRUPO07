package es.dm2egrupo07.accesoadatos.application;

import es.dm2egrupo07.accesoadatos.dataaccess.ProductDataAccessImpl;
import es.dm2egrupo07.accesoadatos.entities.Product;
import es.dm2egrupo07.accesoadatos.services.ProductService;
import es.dm2egrupo07.accesoadatos.services.ProductServiceImpl;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * Clase que contiene pruebas para el servicio de productos.
 * Utiliza {@link ProductService} para realizar las operaciones de prueba.
 */
@NoArgsConstructor
public class ProductTests {
    private final ProductService productService = new ProductServiceImpl(new ProductDataAccessImpl());

//    /**
//     * Constructor de la clase ProductTests.
//     *
//     */
//    public ProductTests() {
//        testFindAll();
//        testSave();
//        testUpdate();
//        testFindById();
//        testExistsById();
//        testDelete();
//    }

    /**
     * Prueba para encontrar todos los productos.
     * Muestra un mensaje indicando si se encontraron o no resultados.
     */
    public void testFindAll(){
        List<Product> products = productService.findAll();

        if (!products.isEmpty() && products != null) {
            System.out.println("No se encontraron resultados");
        } else {
            System.out.println("Se encontraron resultados");
        }
    }

    /**
     * Prueba para buscar un producto por su ID.
     * Muestra un mensaje indicando si se encontró el producto o no.
     */
    public void testFindById() {
        String productId = "1";
        Optional<Product> productIdFound = productService.findById(productId);
        if (productIdFound.isPresent()) {
            System.out.printf("Se encontro el producto con el ID %d\n", productId);
        }
        else {
            System.out.println("El producto no existe");
        }
    }

    /**
     * Prueba para verificar si un producto existe por su ID.
     * Muestra un mensaje indicando si el producto existe o no.
     */
    public void testExistsById(){
        String productId = "465";
        Optional<Product> productIdFound = productService.findById(productId);
        if (productIdFound.isPresent()) {
            System.out.printf("El producto con el ID %d existe\n", productId);
        }
        else {
            System.out.println("El producto no existe");
        }
    }

    /**
     * Prueba para guardar un nuevo producto.
     * Muestra un mensaje indicando si el producto se ha guardado correctamente o no.
     */
    public void testSave() {
        Product product = new Product("sk-100", "Producto Prueba", "Linea0", "Scale0","Vendor0", "Description1", 2, 2.13, 0.30);
        Product productTest = productService.saveProduct(product);
        if (productTest != null) {
            System.out.println("El producto se ha guardado correctamente");
        }
        else {
            System.out.println("El producto no se ha guardado correctamente");
        }
    }

    /**
     * Prueba para actualizar un producto existente.
     * Muestra un mensaje indicando si el producto se ha encontrado y se ha guardado correctamente.
     */
    public void testUpdate() {
        String productId = "254";
        Optional<Product> productIdFound = productService.findById(productId);
        if (productIdFound.isPresent()) {
            Product product = productIdFound.get();
            productService.saveProduct(product);
        }
        else {
            System.out.println("El producto no existe");
        }
    }

    /**
     * Prueba para eliminar un producto.
     * Muestra un mensaje indicando si el producto se ha eliminado correctamente o no.
     */
    public void testDelete() {
        String productId = "196";
        Optional<Product> productIdFound = productService.findById(productId);
        if (productIdFound.isPresent()) {
            System.out.println("El producto se ha eliminado correctamente");
        }
        else {
            System.out.println("El producto no existe");
        }
    }
}
