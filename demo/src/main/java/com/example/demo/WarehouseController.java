package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @PostMapping("/insert")
    public ResponseEntity<?> insertRandomWarehouse() {
        try {
            Warehouse warehouse = createRandomWarehouse();
            warehouseRepository.save(warehouse);
            return new ResponseEntity<>("Random Warehouse inserted successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to insert random warehouse", HttpStatus.INTERNAL_SERVER_ERROR);
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

    private Warehouse createRandomWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Random Warehouse");
        warehouse.setAddress("Random Address");
        // You may add more randomization for products or other fields if needed
        return warehouse;
    }
}
