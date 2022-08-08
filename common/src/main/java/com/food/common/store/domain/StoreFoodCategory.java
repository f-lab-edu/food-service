package com.food.common.store.domain;

import com.food.common.foodCategory.domain.FoodCategory;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.food.common.store.utils.StoreValidationFailureMessage.StoreFoodCategory.NOT_NULL_FOOD_CATEGORY;
import static com.food.common.store.utils.StoreValidationFailureMessage.StoreFoodCategory.NOT_NULL_STORE;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_store_food_category")
@Entity
public class StoreFoodCategory {

    @Embeddable
    @NoArgsConstructor(access = PROTECTED)
    public static class MultiplePk implements Serializable {
        @Column(name = "store_id")
        private Long storeId;

        @Column(name = "food_category_id")
        private Long foodCategoryId;

        public MultiplePk(Long storeId, Long foodCategoryId) {
            this.storeId = storeId;
            this.foodCategoryId = foodCategoryId;
        }
    }

    @EmbeddedId
    private MultiplePk pk = new MultiplePk();

    @Comment("가게")
    @NotNull(message = NOT_NULL_STORE)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    @Comment("음식 카테고리")
    @NotNull(message = NOT_NULL_FOOD_CATEGORY)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "food_category_id", insertable = false, updatable = false)
    private FoodCategory foodCategory;

    public static StoreFoodCategory create(Store store, FoodCategory foodCategory) {
        StoreFoodCategory storeFoodCategory = new StoreFoodCategory();
        storeFoodCategory.store = store;
        storeFoodCategory.foodCategory = foodCategory;

        return storeFoodCategory;
    }
}
