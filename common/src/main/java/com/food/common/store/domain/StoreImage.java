package com.food.common.store.domain;

import com.food.common.image.domain.Image;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.food.common.store.utils.StoreValidationFailureMessages.StoreImage.IMAGE_CANNOT_BE_NULL;
import static com.food.common.store.utils.StoreValidationFailureMessages.StoreImage.STORE_CANNOT_BE_NULL;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_store_image")
@Entity
public class StoreImage {

    @Embeddable
    @NoArgsConstructor(access = PROTECTED)
    public static class MultiplePk implements Serializable {
        @Column(name = "store_id")
        private Long storeId;

        @Column(name = "image_id")
        private Long imageId;

        public MultiplePk(Long storeId, Long imageId) {
            this.storeId = storeId;
            this.imageId = imageId;
        }
    }

    @EmbeddedId
    private MultiplePk pk = new MultiplePk();

    @Comment("가게")
    @NotNull(message = STORE_CANNOT_BE_NULL)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    @Comment("이미지")
    @NotNull(message = IMAGE_CANNOT_BE_NULL)
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    private Image image;

    public static StoreImage create(Store store, Image image) {
        StoreImage storeImage = new StoreImage();
        storeImage.store = store;
        storeImage.image = image;

        return storeImage;
    }
}
