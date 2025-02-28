package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import id.ac.ui.cs.advprog.eshop.model.Item;
import id.ac.ui.cs.advprog.eshop.service.GenericService;

import org.springframework.ui.Model;
public abstract class GenericController<T extends Item> {

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute(getSingularEntityName().toLowerCase(), getEntity());
        return "Create" + getSingularEntityName();
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute T entity) {
        getService().create(entity);
        return "redirect:/"+getSingularEntityName().toLowerCase()+"/list";
    }

    @GetMapping("/list")
    public String listPage(Model model) {
        model.addAttribute(getPluralEntityName().toLowerCase(), getService().findAll()); 
        return getSingularEntityName() + "List";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") String id, Model model) {
        model.addAttribute(getSingularEntityName().toLowerCase(), getService().findById(id));
        return "Edit" + getSingularEntityName();
    }
    
    @PostMapping("/edit")
    protected String editPost(@ModelAttribute T entity, Model model) {
        getService().update(entity.getId(), entity);
        return "redirect:/"+getSingularEntityName().toLowerCase()+"/list";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("Id") String entityId) {
        getService().deleteById(entityId);
        return "redirect:/"+getSingularEntityName().toLowerCase()+"/list";
    }


    protected abstract T getEntity();
    protected abstract String getSingularEntityName();
    protected abstract String getPluralEntityName();
    protected abstract GenericService<T> getService();
}

