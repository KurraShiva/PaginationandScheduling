package com.productInventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.productInventory.dto.Product;
//
//public interface ProductRepository extends JpaRepository<Product, Integer> {
//}

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

}

