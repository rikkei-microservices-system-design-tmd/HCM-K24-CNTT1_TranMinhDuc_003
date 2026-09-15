package org.example.medicineservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.medicineservice.dto.MedicineRequest;
import org.example.medicineservice.model.Medicine;
import org.example.medicineservice.service.MedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }

    @PostMapping
    public ResponseEntity<Medicine> createMedicine(@RequestBody MedicineRequest request) {
        Medicine created = medicineService.createMedicine(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
