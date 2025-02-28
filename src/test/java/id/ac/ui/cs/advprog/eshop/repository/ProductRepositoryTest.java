package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

	@InjectMocks
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getId(),savedProduct.getId());
        savedProduct = productIterator.next();
        assertEquals(product2.getId(),savedProduct.getId());
        assertFalse(productIterator.hasNext());
    }
    
    @Test
    void testFindById_Success() {
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Shampoo A");
        product.setQuantity(10);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(foundProduct);
        assertEquals("Shampoo A", foundProduct.getName());
        assertEquals(10, foundProduct.getQuantity());
    }

    @Test
    void testFindById_Failure_ProductNotFound() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            productRepository.findById("invalid-id");
        });

        assertEquals("Item not found with ID: invalid-id", exception.getMessage());
    }

    @Test
    void testDeleteById_Success() {
        Product product = new Product();
        product.setId("delete-test-id");
        product.setName("Shampoo B");
        product.setQuantity(20);
        productRepository.create(product);

        assertNotNull(productRepository.findById("delete-test-id"));

        productRepository.delete("delete-test-id");

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            productRepository.findById("delete-test-id");
        });

        assertEquals("Item not found with ID: delete-test-id", exception.getMessage());
    }

    @Test
    void testDeleteById_Failure_ProductNotFound() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            productRepository.delete("non-existent-id");
        });

        assertEquals("Item not found with ID: non-existent-id", exception.getMessage());
    }

    
}
