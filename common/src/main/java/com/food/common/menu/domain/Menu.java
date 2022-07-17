package com.food.common.menu.domain;

import com.food.common.store.domain.Store;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Entity @Table(name = "tb_menu")
public class Menu {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private Long storeId;

    private String name;

    private Integer amount;

    @Embedded
    private ImageUrls imageUrls;

    public Long getId() {
        return id;
    }

    public Menu(final Store store, final String name, final Integer amount, final ImageUrls imageUrls) {
        this.storeId = store.getId();
        this.name = name;
        this.amount = amount;
        this.imageUrls = imageUrls;
    }

    @NoArgsConstructor(access = PROTECTED)
    @Embeddable
    public static class ImageUrls {
        private String imageUrls;

        public ImageUrls(List<String> urls) {
            if (CollectionUtils.isEmpty(urls)) return;

            this.imageUrls = mapToString(urls);
        }

        private String mapToString(List<String> urls) {
            return urls.toString();
        }

        public List<String> get() {
            String[] parsedUrls = imageUrls.replace("[", "")
                    .replace("]", "")
                    .split(",");

            return Arrays.stream(parsedUrls)
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
    }
}
