package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDataAccess {

    long count();
    boolean existsById(String id);
    Optional<Product> findById(String id);
    List<Product> findAll();
    Product save(Product product);
    void deleteById(String id);


    /*
    • Constructor en el que reciba las dependencias necesarias, si es que las hay. Las dependencias, siempre que sea posible, se inyectarán como interfaces.
    • long count() – Cuenta el número total de entidades en la tabla.
    • boolean existsById(ID id) – Verifica si una entidad con un ID dado existe.
    • Optional<T> findById(ID id) – Busca una entidad por su ID. Si no existe devuelve Optional vacío.
    • List<T> findAll() – Recupera todas las entidades.
    • T save(T entity) – Guarda una entidad. Si la entidad ya existe, la actualiza. Recibe la entidad que se va a guardar. Devuelve la entidad guardada, con los datos actualizados (id si es nueva)
    • void deleteById(ID id) – Elimina la entidad con el ID especificado.
    * */
}
