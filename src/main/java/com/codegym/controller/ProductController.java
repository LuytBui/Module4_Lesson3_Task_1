package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    public static final String MSG_ACTION_SUCCESSED = "Thao tac thanh cong";
    public static final String MSG_ACTION_FAILED = "Thao tac that bai!";
    @Autowired
    IProductService productService;

    @GetMapping
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("product/list");
        List<Product> products = productService.findAll();
        modelAndView.addObject("products", products);
        modelAndView.addObject("tableCaption", "All products");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showAddProductForm() {
        Product product = new Product();
        return new ModelAndView("product/add", "product", product);
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product product) {
        productService.create(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable int id){
        Product product = productService.findByID(id);
        if (product == null) {
            return new ModelAndView("redirect: /products");
        }

        ModelAndView modelAndView = new ModelAndView("product/delete");
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute Product product){
        boolean success = productService.deleteById(product.getId());
        System.out.println(success);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable int id){
        Product product = productService.findByID(id);
        if (product == null) {
            return new ModelAndView("redirect: /products");
        }

        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product){
        productService.updateById(product.getId(), product);
        return "redirect: /products";
    }

    @GetMapping("/{id}/view")
    public ModelAndView showProductDetail(@PathVariable int id){
        Product product = productService.findByID(id);
        if (product == null) {
            return new ModelAndView("redirect: /products");
        }

        ModelAndView modelAndView = new ModelAndView("product/view");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchProduct(@RequestParam (name = "q") String searchName){
        if (searchName.length() == 0) {
            return new ModelAndView("redirect: /products");
        }

        List<Product> products = productService.searchByName(searchName);
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", products);
        String tableCaption = "Search result for \""+ searchName + "\": " + products.size() + " products";
        modelAndView.addObject("tableCaption", tableCaption);
        return modelAndView;
    }
}
