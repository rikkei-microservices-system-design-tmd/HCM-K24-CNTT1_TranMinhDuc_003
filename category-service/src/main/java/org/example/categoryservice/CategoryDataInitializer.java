package org.example.categoryservice;

import lombok.RequiredArgsConstructor;
import org.example.categoryservice.model.Category;
import org.example.categoryservice.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryDataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            categoryRepository.saveAll(List.of(
                    Category.builder().name("Khang sinh").description("Thuoc khang sinh tri nhiem khuan").build(),
                    Category.builder().name("Giam dau ha sot").description("Thuoc giam dau, ha sot, chong viem").build()
            ));
        }
    }
}
