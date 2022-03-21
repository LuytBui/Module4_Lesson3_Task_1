package com.codegym.service.product;

import com.codegym.dao.product.IProductDAO;
import com.codegym.dao.product.ProductDAO;
import com.codegym.model.Product;

import java.util.List;

public class ProductService implements IProductService{
    private IProductDAO productDAO;

    public ProductService(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findByID(int id) {
        return productDAO.findByID(id);
    }

    @Override
    public boolean create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public boolean deleteById(int id) {
        return productDAO.deleteById(id);
    }

    @Override
    public boolean updateById(int id, Product product) {
        return productDAO.updateById(id, product);
    }

    @Override
    public List<Product> searchByName(String searchName) {
        return productDAO.searchByName(searchName);
    }
}
