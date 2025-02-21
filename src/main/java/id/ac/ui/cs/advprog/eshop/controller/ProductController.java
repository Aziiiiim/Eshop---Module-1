package id.ac.ui.cs.advprog.eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    /**
     * Displays the HomePage page.
     */
    @GetMapping("/")
    public String home(Model model) {
        return "HomePage";
    }
    
    /**
     * Displays the product creation page with an empty Product object.
     */
    @GetMapping("/product/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    /**
     * Handles form submission for creating a new product.
     * Redirects to the product list after successful creation.
     */
    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    /**
     * Displays the list of all products.
     */
    @GetMapping("/product/list")
    public String createListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    /**
     * Displays the product edit page for the given product ID.
     * Throws an exception if the product is not found.
     */
    @GetMapping("/product/edit/{id}")
    public String editProductPage(@PathVariable("id") String id, Model model) {
        Product product = service.findById(id);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    /**
     * Handles form submission for updating a product.
     * Redirects to the product list after a successful update.
     */
    @PostMapping("/product/edit/{id}")
    public String editProductPost(@PathVariable("id") String id, @ModelAttribute Product product) {
        service.update(id, product);
        return "redirect:/product/list";
    }

    /**
     * Deletes a product by its ID and redirects to the product list.
     */
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id, Model model) {
        service.deleteProduct(id);
        return "redirect:/product/list";
    }
}
