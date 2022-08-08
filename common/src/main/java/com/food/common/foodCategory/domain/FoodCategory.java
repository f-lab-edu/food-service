package com.food.common.foodCategory.domain;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static com.food.common.foodCategory.utils.FoodCategoryFailureMessages.BETWEEN_LENGTH_OF_NAME;
import static com.food.common.foodCategory.utils.FoodCategoryFailureMessages.NOT_BLANK_NAME;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_food_category")
@Entity
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "food_category_id")
    private Long id;

    @Comment("음식 카테고리명")
    @NotBlank(message = NOT_BLANK_NAME)
    @Length(max = 15, message = BETWEEN_LENGTH_OF_NAME)
    private String name;

    public static FoodCategory create(String name) {
        FoodCategory foodCategory = new FoodCategory();
        foodCategory.name = name;

        return foodCategory;
    }
}
