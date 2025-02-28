package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.GenericService;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
@Controller
@RequestMapping("/product")
public class ProductController extends GenericController<Product>{

	@Autowired
    private ProductServiceImpl service;

    @Override
    protected Product getEntity() {
        return new Product();
    }
    @Override
    protected GenericService<Product> getService() {
        return service;
    }

	@Override
	protected String getSingularEntityName() {
		return "Product";
	}

	@Override
	protected String getPluralEntityName() {
		return "Products";
	}
}
