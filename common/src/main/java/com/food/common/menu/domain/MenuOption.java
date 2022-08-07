package com.food.common.menu.domain;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_menu_option")
@Entity
public class MenuOption {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "menu_option_id")
    private Long id;

    @Comment("메뉴")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Comment("선택옵션명")
    @Length(max = 30)
    @NotBlank
    private String name;

    @Comment("최소 선택 개수")
    @Size(max = 100)
    @NotNull
    private Byte minSize;

    @Comment("최대 선택 개수")
    @Size(max = 100)
    @NotNull
    private Byte maxSize;

    public static MenuOption menuOption(Menu menu, String name, Byte minSize, Byte maxSize) {
        MenuOption menuOption = new MenuOption();
        menuOption.menu = menu;
        menuOption.name = name;
        menuOption.minSize = minSize;
        menuOption.maxSize = maxSize;

        return menuOption;
    }
}
