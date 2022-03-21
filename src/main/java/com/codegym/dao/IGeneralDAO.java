package com.codegym.dao;

import java.util.List;

public interface IGeneralDAO<T> {
    List<T> findAll();

    T findByID(int id);

    boolean create(T t);  // thanh cong = true

    boolean deleteById(int id);  // thanh cong = true

    boolean updateById(int id, T t);  // thanh cong = true


}
