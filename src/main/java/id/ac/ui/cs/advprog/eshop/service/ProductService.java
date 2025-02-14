package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {

    // Creates and stores a new product
    Product create(Product product);

    // Retrieves all products
    List<Product> findAll();

    // Finds a product by its ID, throws an error if not found
    Product findById(String id);

    // Updates an existing product by its ID
    void update(String id, Product product);

    // Deletes a product by its ID
    void deleteProduct(String id);
}
