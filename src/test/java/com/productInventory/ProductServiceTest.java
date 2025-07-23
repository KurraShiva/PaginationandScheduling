package com.productInventory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.opencsv.bean.CsvToBeanBuilder;
import com.productInventory.repo.ProductRepository;
import com.productInventory.service.ProductService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testImportCSV() throws Exception {
        // Mock MultipartFile
        MultipartFile file = mock(MultipartFile.class);
        InputStream is = this.getClass().getResourceAsStream("/test-products.csv");
        when(file.getInputStream()).thenReturn(is);

        productService.importCSVA(file);

        verify(productRepository, times(1)).saveAll(anyList());
    }
}
