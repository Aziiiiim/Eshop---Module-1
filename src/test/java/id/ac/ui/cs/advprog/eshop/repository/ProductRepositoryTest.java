package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

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
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(),savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(),savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }
    
    @Test
    void testFindById_Success() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Shampoo A");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(foundProduct);
        assertEquals("Shampoo A", foundProduct.getProductName());
        assertEquals(10, foundProduct.getProductQuantity());
    }

    @Test
    void testFindById_Failure_ProductNotFound() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            productRepository.findById("invalid-id");
        });

        assertEquals("Product not found with ID: invalid-id", exception.getMessage());
    }

    @Test
    void testDeleteById_Success() {
        Product product = new Product();
        product.setProductId("delete-test-id");
        product.setProductName("Shampoo B");
        product.setProductQuantity(20);
        productRepository.create(product);

        assertNotNull(productRepository.findById("delete-test-id"));

        productRepository.deleteById("delete-test-id");

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            productRepository.findById("delete-test-id");
        });

        assertEquals("Product not found with ID: delete-test-id", exception.getMessage());
    }

    @Test
    void testDeleteById_Failure_ProductNotFound() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            productRepository.deleteById("non-existent-id");
        });

        assertEquals("Product not found with ID: non-existent-id", exception.getMessage());
    }

    
}
