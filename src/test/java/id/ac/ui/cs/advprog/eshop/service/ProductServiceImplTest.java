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
        sampleProduct.setProductId("test-id");
        sampleProduct.setProductName("Test Product");
        sampleProduct.setProductQuantity(50);
    }

    @Test
    void testCreateProduct_Success() {
        when(productRepository.create(sampleProduct)).thenReturn(sampleProduct);

        Product createdProduct = productService.create(sampleProduct);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getProductName());
        assertEquals(50, createdProduct.getProductQuantity());

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
        assertEquals("Test Product", result.get(0).getProductName());
    }

    @Test
    void testFindById_Success() {
        when(productRepository.findById("test-id")).thenReturn(sampleProduct);

        Product foundProduct = productService.findById("test-id");

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getProductName());
        assertEquals(50, foundProduct.getProductQuantity());

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
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(30);

        when(productRepository.findById("test-id")).thenReturn(sampleProduct);

        productService.update("test-id", updatedProduct);

        assertEquals("Updated Product", sampleProduct.getProductName());
        assertEquals(30, sampleProduct.getProductQuantity());

        verify(productRepository, times(1)).findById("test-id");
    }

    @Test
    void testDeleteProduct_Success() {
        doNothing().when(productRepository).deleteById("test-id");

        productService.deleteProduct("test-id");

        verify(productRepository, times(1)).deleteById("test-id");
    }

    @Test
    void testDeleteProduct_Failure_ProductNotFound() {
        doThrow(new NoSuchElementException("Product not found")).when(productRepository).deleteById("invalid-id");

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            productService.deleteProduct("invalid-id");
        });

        assertEquals("Product not found", exception.getMessage());
    }
}
