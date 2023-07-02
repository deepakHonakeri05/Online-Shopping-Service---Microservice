package com.pro.deepak.product.service.repository;

import com.pro.deepak.product.service.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
