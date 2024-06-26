package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/insert")
    public ResponseEntity<?> insertRandomWarehouse() {
        try {
            Warehouse warehouse = createRandomWarehouse();
            warehouseRepository.save(warehouse);
            return new ResponseEntity<>("Random Warehouse inserted successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<?> retrieveWarehouse(@PathVariable("id") Integer id) {
        try {
            Warehouse warehouse = warehouseRepository.findById(id).orElse(null);
            if (warehouse != null) {
                return new ResponseEntity<>(warehouse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Warehouse not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve warehouse", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllWarehouses() {
        try {
            List<Warehouse> warehouses = warehouseRepository.findAll();
            if (!warehouses.isEmpty()) {
                return new ResponseEntity<>(warehouses, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No warehouses found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve warehouses", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{warehouseId}/products/{productId}")
    public ResponseEntity<?> retrieveProductInWarehouse(@PathVariable("warehouseId") Integer warehouseId, @PathVariable("productId") Integer productId) {
        try {
            // Retrieve the specific warehouse with all its products
            Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouseId);
            if (warehouseOptional.isPresent()) {
                Warehouse warehouse = warehouseOptional.get();
                // Iterate through products to find the specific product
                for (Product product : warehouse.getProducts()) {
                    if (product.getId() == (productId)) {
                        return new ResponseEntity<>(product, HttpStatus.OK);
                    }
                }
                return new ResponseEntity<>("Product not found in the specified warehouse", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("Warehouse not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private Warehouse createRandomWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Random Warehouse" + Math.random());
        warehouse.setAddress("Random Address" + Math.random());

        // Generate random products
        List<Product> products = generateRandomProducts();
        for (Product product : products) {
            product.setWarehouse(warehouse);
        }
        warehouse.setProducts(products);

        return warehouse;
    }

    private List<Product> generateRandomProducts() {
        List<Product> products = new ArrayList<>();
        Random random = new Random();
        int numProducts = random.nextInt(5) + 1; // Random number of products (1 to 5)

        for (int i = 0; i < numProducts; i++) {
            Product product = new Product();
            product.setName("Product " + (i + 1));
            product.setPrice(random.nextInt(100)); // Random price (0 to 99)
            products.add(product);
        }
        return products;
    }

    @PatchMapping("/update/{warehouseId}/add-product")
    public ResponseEntity<?> addProductToWarehouse(@PathVariable("warehouseId") Integer warehouseId) {
        try {
            Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouseId);
            if (warehouseOptional.isPresent()) {
                Warehouse warehouse = warehouseOptional.get();
                Product newProduct = createRandomProduct();
                warehouse.getProducts().add(newProduct);
                newProduct.setWarehouse(warehouse);  // Set the back reference if needed
                warehouseRepository.save(warehouse);
                return new ResponseEntity<>("Product added successfully to Warehouse", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Warehouse not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add product to warehouse", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Product createRandomProduct() {
        Random random = new Random();
        Product product = new Product();
        product.setName("Product " + (random.nextInt(1000) + 1));
        product.setPrice(random.nextInt(100));  // Random price from 0 to 99
        return product;
    }

}
