package com.dona.ecom_project.controller;

import com.dona.ecom_project.model.Products;
import com.dona.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Products>> getProduct(){
        return new ResponseEntity<>(service.getProduct() , HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Products> getProduct(@PathVariable("id") int id){
        Products products = service.getProductById(id);
        if(products != null){
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        }

        @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart("product") Products product , @RequestPart("imageFile") MultipartFile imageFile){
        try{
            Products pro = service.addProduct(product , imageFile);
            return new ResponseEntity<>(pro , HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

        @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Products product = service.getProductById(productId);
        byte[] imageFile = product.getImageDate();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
        }

            @PutMapping("/product/{id}")
        public ResponseEntity<String> updateProduct(@PathVariable int id  , @RequestPart Products product , @RequestPart MultipartFile imageFile){
            Products product1 = null;
            try {
                product1 = service.updateProduct(id , product , imageFile);
            } catch (IOException e) {
                return new ResponseEntity<>("Failed to update" , HttpStatus.BAD_REQUEST);
            }
            if(product1 != null){
                return  new ResponseEntity<>("Updated" , HttpStatus.OK);
            }
            else{
                return  new ResponseEntity<>("Failed to update" , HttpStatus.BAD_REQUEST);
            }
        }

            @DeleteMapping("/product/{id}")
        public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Products product = service.getProductById(id);
        if(product != null){
            service.deleteProduct(id);
            return  new ResponseEntity<>("Deleted" , HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<>("Product Not Found" , HttpStatus.NOT_FOUND);
        }
    }

//        @GetMapping("/products/search")
//    public ResponseEntity<List<Products>> searchProducts(@RequestParam String keyword){
//        List<Products> products = service.searchProducts(keyword);
//        return new ResponseEntity<>(products , HttpStatus.OK);
//        }
}
