package com.springecom.ecom.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springecom.ecom.modal.Product;
import com.springecom.ecom.repo.ProductRepo;

@Service    
public class ProductService {


    @Autowired
    private ProductRepo repo;
    
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(new Product());

    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imagFile) throws IOException {
        product.setImageData(imagFile.getBytes());
        product.setImageType(imagFile.getContentType());
        product.setImageName(imagFile.getOriginalFilename());
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {

        
    return repo.searchProducts(keyword);
    }


}
