package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setId("test-id");
        sampleProduct.setName("Test Product");
        sampleProduct.setQuantity(50);
    }

    @Test
    void testCreateProduct_Success() {
        when(productRepository.create(sampleProduct)).thenReturn(sampleProduct);

        Product createdProduct = productService.create(sampleProduct);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
        assertEquals(50, createdProduct.getQuantity());

        verify(productRepository, times(1)).create(sampleProduct);
    }

    @Test
    void testFindAllProducts_Success() {
        List<Product> products = new ArrayList<>();
        products.add(sampleProduct);

        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> result = productService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getName());
    }

    @Test
    void testFindById_Success() {
        when(productRepository.findById("test-id")).thenReturn(sampleProduct);

        Product foundProduct = productService.findById("test-id");

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getName());
        assertEquals(50, foundProduct.getQuantity());

        verify(productRepository, times(1)).findById("test-id");
    }

    @Test
    void testFindById_Failure_ProductNotFound() {
        when(productRepository.findById("invalid-id")).thenThrow(new NoSuchElementException("Product not found"));

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            productService.findById("invalid-id");
        });

        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    void testUpdateProduct_Success() {
    	Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setQuantity(30);

        when(productRepository.update("test-id", updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.update("test-id", updatedProduct);

        assertEquals("Updated Product", result.getName());
        assertEquals(30, result.getQuantity()); 

        verify(productRepository, times(1)).update("test-id", updatedProduct);
    }

    @Test
    void testDeleteProduct_Success() {
        doNothing().when(productRepository).delete("test-id");

        productService.deleteById("test-id");

        verify(productRepository, times(1)).delete("test-id");
    }

    @Test
    void testDeleteProduct_Failure_ProductNotFound() {
        doThrow(new NoSuchElementException("Product not found")).when(productRepository).delete("invalid-id");

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            productService.deleteById("invalid-id");
        });

        assertEquals("Product not found", exception.getMessage());
    }
}
