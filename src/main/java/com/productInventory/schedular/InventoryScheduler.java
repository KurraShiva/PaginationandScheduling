package com.productInventory.schedular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.productInventory.dto.Product;
import com.productInventory.repo.ProductRepository;

import java.util.List;

@Component
public class InventoryScheduler {

    @Autowired
    private ProductRepository productRepository;

    @Scheduled(cron = "0 0 9 * * ?") // Runs daily at 9 AM
    public void reportLowStockProducts() {
        List<Product> lowStockProducts = productRepository.findAll((root, query, cb) ->
                cb.lessThan(root.get("quantity"), 10));

      
        lowStockProducts.forEach(product ->
                System.out.println("Low stock: " + product.getProdName() + " - Quantity: " + product.getProdQuantity()));
    }
}
