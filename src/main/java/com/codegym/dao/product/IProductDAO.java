package com.codegym.dao.product;

import com.codegym.dao.IGeneralDAO;
import com.codegym.model.Product;

import java.util.List;

public interface IProductDAO extends IGeneralDAO<Product> {
    List<Product> searchByName(String searchName);
}
