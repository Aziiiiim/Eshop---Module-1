package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class HomeController {
	@GetMapping("/")
    public String home(Model model) {
        return "HomePage";
    }
}
