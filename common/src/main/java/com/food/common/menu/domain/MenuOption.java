package com.food.common.menu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_menu_option")
@Entity(name = "MenuOption")
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
    @Max(100)
    @NotNull
    private Byte minSize;

    @Comment("최대 선택 개수")
    @Max(100)
    @NotNull
    private Byte maxSize;

    @OneToMany(mappedBy = "option")
    private List<MenuSelection> selections = new ArrayList<>();

    public static MenuOption create(Menu menu, String name, Byte minSize, Byte maxSize) {
        MenuOption menuOption = new MenuOption();
        menuOption.menu = menu;
        menuOption.name = name;
        menuOption.minSize = minSize;
        menuOption.maxSize = maxSize;

        return menuOption;
    }

    public Long getMenuId() {
        return menu.getId();
    }

    public void initSelections(List<MenuSelection> selections) {
        this.selections.addAll(selections);
    }

    public List<MenuSelection> getSelections() {
        return Collections.unmodifiableList(selections);
    }
}
