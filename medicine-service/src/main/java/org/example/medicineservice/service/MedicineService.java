package org.example.medicineservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.example.medicineservice.client.CategoryClient;
import org.example.medicineservice.dto.CategoryDto;
import org.example.medicineservice.dto.MedicineRequest;
import org.example.medicineservice.exception.CategoryNotFoundException;
import org.example.medicineservice.model.Medicine;
import org.example.medicineservice.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final CategoryClient categoryClient;

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine createMedicine(MedicineRequest request) {
        if (request.getCategoryId() == null) {
            throw new CategoryNotFoundException("Category ID must not be null");
        }

        try {
            CategoryDto category = categoryClient.getCategoryById(request.getCategoryId());
            if (category == null || category.getId() == null) {
                throw new CategoryNotFoundException("Category not found with id: " + request.getCategoryId());
            }
        } catch (FeignException.NotFound e) {
            throw new CategoryNotFoundException("Category not found with id: " + request.getCategoryId());
        } catch (Exception e) {
            throw new CategoryNotFoundException("Category not found with id: " + request.getCategoryId());
        }

        Medicine medicine = Medicine.builder()
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .categoryId(request.getCategoryId())
                .description(request.getDescription())
                .build();

        return medicineRepository.save(medicine);
    }
}
