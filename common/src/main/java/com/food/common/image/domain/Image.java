package com.food.common.image.domain;

import com.food.common.common.domain.BaseTimeEntity;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_image")
@Entity
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Comment("저장 경로")
    @Length(max = 200)
    @NotBlank
    private String path;

    @Comment("파일명")
    @Length(max = 50)
    @NotBlank
    private String name;

    public static Image create(String path, String name) {
        Image image = new Image();
        image.path = path;
        image.name = name;

        return image;
    }
}
