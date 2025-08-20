package com.dona.ecom_project.service;

import com.dona.ecom_project.model.Products;
import com.dona.ecom_project.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Products> getProduct() {
        return repo.findAll();
    }

    public Products getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Products addProduct(Products products, MultipartFile imageFile) throws IOException {
        products.setImageName((imageFile.getOriginalFilename()));
        products.setImageType(imageFile.getContentType());
        products.setImageDate(imageFile.getBytes());
        return repo.save(products);
    }

        public Products updateProduct(int id, Products product, MultipartFile imageFile) throws IOException {
        product.setImageDate(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return repo.save(product);
    }

        public void deleteProduct(int id) {
         repo.deleteById(id);
    }

//    public List<Products> searchProducts(String keyword) {
//        return repo.searchProducts(keyword);
//    }

}
