package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    // Adds a new product to the repository
    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    // Returns all stored products
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    // Finds a product by its ID. Throws an exception if not found.
    public Product findById(String id) {
        for (Product p : productData) {
            if (p.getProductId().equals(id)) {
                return p;
            }
        }
        throw new NoSuchElementException("Product not found with ID: " + id);
    }

    // Deletes a product by its ID if it exists
    public void deleteById(String id) {
        Iterator<Product> iterator = productData.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getProductId().equals(id)) {
                iterator.remove();
                return;
            }
        }
        throw new NoSuchElementException("Product not found with ID: " + id);
    }
}
