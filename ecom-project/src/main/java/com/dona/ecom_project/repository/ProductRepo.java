package com.dona.ecom_project.repository;

import com.dona.ecom_project.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Products, Integer>{

//    @Query("SELECT p from Products p where"+
//    "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//    "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//    "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//    "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//    List<Products> searchProducts(String keyword);
}
