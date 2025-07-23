package com.productInventory.specification;


import org.springframework.data.jpa.domain.Specification;

import com.productInventory.dto.Product;

//public class ProductSpecification {
//
//    public static Specification<Product> hasCategory(String category) {
//        return (root, query, cb) -> category == null ? null : cb.equal(root.get("category"), category);
//    }
//
//    public static Specification<Product> hasPriceBetween(Double minPrice, Double maxPrice) {
//        return (root, query, cb) -> {
//            if (minPrice == null && maxPrice == null) return null;
//            if (minPrice == null) return cb.le(root.get("price"), maxPrice);
//            if (maxPrice == null) return cb.ge(root.get("price"), minPrice);
//            return cb.between(root.get("price"), minPrice, maxPrice);
//        };
//    }
//}




public class ProductSpecification {

    public static Specification<Product> hasCategory(String category) {
        return (root, query, cb) -> category == null ? null : cb.equal(root.get("prodCategory"), category);
    }

    public static Specification<Product> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, cb) -> {
            if (minPrice == null && maxPrice == null) return null;
            if (minPrice == null) return cb.le(root.get("prodPrice"), maxPrice);
            if (maxPrice == null) return cb.ge(root.get("prodPrice"), minPrice);
            return cb.between(root.get("prodPrice"), minPrice, maxPrice);
        };
    }
}

