package com.food.common.mock.image;

import com.food.common.image.domain.Image;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockImage {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private String path = "/review/1";
        private String name = "test.jpg";

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Image build() {
            return Image.create(path, name);
        }
    }
}
