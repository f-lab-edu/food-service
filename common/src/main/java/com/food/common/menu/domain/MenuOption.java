package com.food.common.menu.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "tb_menu_option")
public class MenuOption {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "menu_option_id")
    private Long id;

    private Long menuId;

    private String title;

    private String selection;

    private Integer additionalAmount;

    private Boolean isRequired;

    public MenuOption(final Menu menu, final String title, final String selection, final Integer additionalAmount, final Boolean isRequired) {
        this.menuId = menu.getId();
        this.title = title;
        this.selection = selection;
        this.additionalAmount = additionalAmount;
        this.isRequired = isRequired;
    }

    public String getTitle() {
        return title;
    }

    public String getSelection() {
        return selection;
    }
}
