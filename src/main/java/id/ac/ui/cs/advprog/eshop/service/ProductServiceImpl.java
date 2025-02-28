package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

@Repository
public class ProductServiceImpl extends GenericService<Product>{
	private final ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.repository = productRepository;
    }
}
