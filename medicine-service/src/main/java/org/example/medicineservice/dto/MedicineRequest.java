package org.example.medicineservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineRequest {
    private String name;
    private Double price;
    private Integer quantity;
    private Long categoryId;
    private String description;
}
