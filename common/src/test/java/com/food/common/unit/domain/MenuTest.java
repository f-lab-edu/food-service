package com.food.common.unit.domain;

import com.food.common.menu.domain.Menu;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTest {

    @Test
    void shouldGetImageUrlsAsListType() {
        List<String> urls = List.of("https://imageUrlA.jpg", "https://imageUrlB.jpg", "https://imageUrlC.jpg");
        Menu.ImageUrls imageUrls = new Menu.ImageUrls(urls);

        List<String> result = imageUrls.get();
        assertThat(result)
                .containsExactly("https://imageUrlA.jpg", "https://imageUrlB.jpg", "https://imageUrlC.jpg");
    }
}
