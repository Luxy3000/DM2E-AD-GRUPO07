package es.dm2egrupo07.accesoadatos;

import es.dm2egrupo07.accesoadatos.application.ProductTests;
import es.dm2egrupo07.accesoadatos.dataaccess.ProductDataAccess;
import es.dm2egrupo07.accesoadatos.dataaccess.ProductDataAccessImpl;
import es.dm2egrupo07.accesoadatos.services.ProductService;
import es.dm2egrupo07.accesoadatos.services.ProductServiceImpl;

import java.util.Scanner;

public class Programa {

    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {


        ProductDataAccess productDataAccess = new ProductDataAccessImpl();
        ProductService productService = new ProductServiceImpl(productDataAccess);
        ProductTests productTests = new ProductTests(productService);

        // Ejecutar las pruebas
        productTests.testFindAll();
        productTests.testFindById();
        productTests.testExistsById();
        productTests.testSave();
        productTests.testUpdate();
        productTests.testDelete();

    }
}