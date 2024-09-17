package com.bonappetit.init;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.enums.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CategoryInit implements CommandLineRunner {
    Map<String, String> map = new LinkedHashMap<>();

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() == 0) {
            map.put("MAIN_DISH", "Heart of the meal, substantial and satisfying; main dish delights taste buds.");
            map.put("DESSERT", "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.");
            map.put("COCKTAIL", "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.");

            for (Map.Entry<String, String> entry : map.entrySet()) {
                categoryRepository.saveAndFlush(new Category(CategoryName.valueOf(entry.getKey()), entry.getValue()));
            }
        }
    }
}
