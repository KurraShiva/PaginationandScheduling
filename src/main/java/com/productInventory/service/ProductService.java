package com.productInventory.service;

import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.productInventory.dto.Product;
import com.productInventory.repo.ProductRepository;
import com.productInventory.specification.ProductSpecification;

@Service
public class ProductService {

	@Autowired
    private ProductRepository repository;

    public Page<Product> getAllProducts(String category, Double minPrice, Double maxPrice, Pageable pageable) {
        Specification<Product> spec = Specification.where(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.hasPriceBetween(minPrice, maxPrice));
        return repository.findAll(spec, pageable);
    }
    
    public void saveAll(List<Product> products) {
        repository.saveAll(products);
    }
    
    public List<Product> getAll() {
        return repository.findAll();
    }
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Integer id, Product product) {
        product.setProdId(id);
        return repository.save(product);
    }

    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }

    public Product getProductById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    
    public void importCSVA(MultipartFile file) throws Exception {
        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {
            CsvToBean<Product> csvToBean = new CsvToBeanBuilder<Product>(reader)
                    .withType(Product.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Product> products = csvToBean.parse();
            repository.saveAll(products);
        }
    }

    public Page<Product> getProducts(String category, Double minPrice, Double maxPrice, Pageable pageable) {
        Specification<Product> spec = Specification.where(null);

        if (category != null && !category.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                cb.equal(root.get("prodCategory"), category)       // ← prodCategory
            );
        }
        if (minPrice != null) {
            spec = spec.and((root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("prodPrice"), minPrice) // ← prodPrice
            );
        }
        if (maxPrice != null) {
            spec = spec.and((root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("prodPrice"), maxPrice)   // ← prodPrice
            );
        }

        return repository.findAll(spec, pageable);
    }




}

