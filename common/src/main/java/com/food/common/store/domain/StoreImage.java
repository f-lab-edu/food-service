package com.food.common.store.domain;

import com.food.common.image.domain.Image;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_store_image")
@Entity
public class StoreImage {

    @Embeddable
    @NoArgsConstructor(access = PROTECTED)
    public static class MultiplePk implements Serializable {
        private Long storeId;

        private Long imageId;

        public MultiplePk(Long storeId, Long imageId) {
            this.storeId = storeId;
            this.imageId = imageId;
        }
    }

    @EmbeddedId
    private MultiplePk pk = new MultiplePk();

    @Comment("가게")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Comment("이미지")
    @NotNull
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    public static StoreImage create(Store store, Image image) {
        StoreImage storeImage = new StoreImage();
        storeImage.store = store;
        storeImage.image = image;

        return storeImage;
    }
}
