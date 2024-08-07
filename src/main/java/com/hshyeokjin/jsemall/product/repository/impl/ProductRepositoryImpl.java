package com.hshyeokjin.jsemall.product.repository.impl;

import com.hshyeokjin.jsemall.common.paging.Page;
import com.hshyeokjin.jsemall.common.util.DbConnection;
import com.hshyeokjin.jsemall.product.entity.Product;
import com.hshyeokjin.jsemall.product.exception.ProductRegisterException;
import com.hshyeokjin.jsemall.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public Product findByProductId(int productId) {
        String sql =
                "SELECT productId, categoryId, name, price, description, image FROM Product WHERE productId=?";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, productId);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                Product product = new Product(
                        rs.getInt("productId"),
                        rs.getInt("categoryId"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getString("image")
                );

                return product;
            }
        } catch (SQLException e) {
            log.error("ProductRepositoryImpl.findById() :", e);
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public int save(Product product) {
        String sql =
                "INSERT INTO Product (productId, categoryId, name, price, description, image) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql);

            psmt.setInt(1, product.getProductId());
            psmt.setInt(2, product.getCategoryId());
            psmt.setString(3, product.getName());
            psmt.setInt(4, product.getPrice());
            psmt.setString(5, product.getDescription());
            psmt.setString(6, product.getImage());

            return psmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new ProductRegisterException("상품 등록(save) 오류", e);
        }
    }


    @Override
    public int update(Product product) {
        String sql =
                "UPDATE Product SET categoryId = ?,  name = ?, price = ?, Description = ? WHERE productId = ?";

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql);

            psmt.setInt(1, product.getCategoryId());
            psmt.setString(2, product.getName());
            psmt.setDouble(3, product.getPrice());
            psmt.setString(4, product.getDescription());
            psmt.setInt(5, product.getProductId());

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("상품 수정(update) 오류", e);
        }
    }

    @Override
    public int deleteByProductId(int productId) {
        String sql = "delete from Product where productId=?";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql);

            psmt.setInt(1, productId);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByProductId(int productId) {
        String sql = "SELECT COUNT(*) FROM Product WHERE ProductID = ?";

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, productId);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error counting products by productId", e);
        }

        return 0;
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT productId, categoryId, name, price, image, description FROM Product";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("productId"),
                        rs.getInt("categoryId"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("image"),
                        rs.getString("description")
                );
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Page<Product> findProductsByPage(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        String sql = "SELECT productId, categoryId, name, image, price, description FROM Product LIMIT ?, ?";

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, startIndex);
            psmt.setInt(2, pageSize);
            ResultSet rs = psmt.executeQuery();
            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("productId"),
                        rs.getInt("categoryId"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getString("image")
                        ));
            }
            int total = 0;
            if(!products.isEmpty()){
                total = findAll().size();
            }
            return new Page<Product>(products,total);
        } catch (SQLException e) {
            log.error("ProductRepositoryImpl.findProductsByPage() :", e);
            throw new RuntimeException();
        }
    }
}
