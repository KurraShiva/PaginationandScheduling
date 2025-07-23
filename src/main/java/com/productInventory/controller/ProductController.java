package com.productInventory.controller;


import java.io.Writer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.productInventory.dto.Product;
import com.productInventory.service.ProductService;

import org.springframework.http.MediaType;


import jakarta.servlet.http.HttpServletResponse;


@RestController
//@RequestMapping("/api/products") 
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/getAll")
    public Page<Product> getAllProducts(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size,
        @RequestParam(defaultValue = "prodId,asc") String[] sort
    ) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        return service.getAllProducts(category, minPrice, maxPrice, pageable);
    }
    
    
    @GetMapping("/getAppProd")
    public Page<Product> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "prodId,asc") String[] sort) {

        Sort.Direction direction = sort[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));

        return service.getProducts(category, minPrice, maxPrice, pageable);
    }
    
    
    
    
//    @PostMapping("/upload-csv")
//    public String uploadCSV(@RequestParam("file") MultipartFile file) {
//        try (Reader reader = new InputStreamReader(file.getInputStream())) {
//            CsvToBean<Product> csvToBean = new CsvToBeanBuilder<Product>(reader)
//                    .withType(Product.class)
//                    .withIgnoreLeadingWhiteSpace(true)
//                    .build();
//
//            List <Product> products = csvToBean.parse();
//            service.saveAll(products);
//            return "CSV upload successful. Records added: " + products.size();
//        } catch (Exception e) {
//            return "Error uploading CSV: " + e.getMessage();
//        }
//    }

    @PostMapping(value = "/upload-csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadCSV(@RequestParam("file") MultipartFile file) {
        try {
            service.importCSVA(file);
            return "CSV upload successful.";
        } catch (Exception e) {
            return "Error uploading CSV: " + e.getMessage();
        }
    }
    
//    @PostMapping("/upload-static-csv")
//    public String uploadStaticCSV() {
//        try (Reader reader = new InputStreamReader(new ClassPathResource("templates/product.csv").getInputStream())) {
//            CsvToBean<Product> csvToBean = new CsvToBeanBuilder<Product>(reader)
//                    .withType(Product.class)
//                    .withIgnoreLeadingWhiteSpace(true)
//                    .build();
//
//            List<Product> products = csvToBean.parse();
//            service.saveAll(products);
//            return "Static CSV upload successful. Records added: " + products.size();
//        } catch (Exception e) {
//            return "Error uploading static CSV: " + e.getMessage();
//        }
//    }


    
    @GetMapping("/export-csv")
    public void exportCSV(HttpServletResponse response) {
        try {
           
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=products.csv");

            List<Product> products = service.getAll(); 

            Writer writer = response.getWriter();

            StatefulBeanToCsv<Product> beanToCsv = new StatefulBeanToCsvBuilder<Product>(writer)
                    .withApplyQuotesToAll(false) 
                    .build();


            beanToCsv.write(products);

        } catch (Exception e) {
            throw new RuntimeException("Failed to export CSV: " + e.getMessage());
        }
    }


    @PostMapping("/save") 
    public Product create(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product product) {
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteProduct(id);
    }

    @GetMapping("/get/{id}")
    public Product getById(@PathVariable Integer id) {
        return service.getProductById(id);
    }
}
