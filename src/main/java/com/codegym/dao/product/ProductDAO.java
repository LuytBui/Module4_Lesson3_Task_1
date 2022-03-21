package com.codegym.dao.product;

import com.codegym.dao.DBConnection;
import com.codegym.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO{
    public static final String SQL_SELECT_ALL_PRODUCTS = "select * from products";
    public static final String SQL_SELECT_PRODUCT_BY_ID = "select * from products where id = ?";
    public static final String SQL_UPDATE_PRODUCT_BY_ID = "update products set name=?, price=?, description=?, image=? where id=?";
    public static final String SQL_DELETE_PRODUCT_BY_ID = "delete from products where id = ?;";
    public static final String SQL_CREATE_PRODUCT = "insert into products (name, price, description, image) values (?, ?, ?, ?);";
    private Connection connection = DBConnection.getConnection();
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                long price = resultSet.getLong("price");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");

                Product product = new Product(id, name, price, description, image);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findByID(int searchID) {
        Product product = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, searchID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                long price = resultSet.getLong("price");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                product = new Product(id, name, price, description, image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean create(Product product) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(SQL_CREATE_PRODUCT);
            prepareStatement.setString(1, product.getName());
            prepareStatement.setLong(2, product.getPrice());
            prepareStatement.setString(3, product.getDescription());
            prepareStatement.setString(4, product.getImage());
            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_PRODUCT_BY_ID);
            prepareStatement.setInt(1, id);
            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, Product product) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT_BY_ID);
            prepareStatement.setString(1, product.getName());
            prepareStatement.setLong(2, product.getPrice());
            prepareStatement.setString(3, product.getDescription());
            prepareStatement.setString(4, product.getImage());
            prepareStatement.setInt(5, id);
            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> searchByName(String searchName) {
        String searchPattern = "%" + searchName + "%";
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from products where name like ?");
            preparedStatement.setString(1, searchPattern);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                long price = resultSet.getLong("price");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");

                Product product = new Product(id, name, price, description, image);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
