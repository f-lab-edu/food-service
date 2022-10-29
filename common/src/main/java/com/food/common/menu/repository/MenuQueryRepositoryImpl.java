package com.food.common.menu.repository;

import com.food.common.menu.domain.Menu;
import com.food.common.menu.domain.MenuOption;
import com.food.common.menu.domain.MenuSelection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.food.common.menu.domain.QMenu.menu;
import static com.food.common.menu.domain.QMenuOption.menuOption;
import static com.food.common.menu.domain.QMenuSelection.menuSelection;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@RequiredArgsConstructor
@Repository
public class MenuQueryRepositoryImpl implements MenuQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Menu> findMenusByIdIn(List<Long> menuIds) {
        List<Menu> result = queryFactory
                .selectFrom(menu)
                .where(menu.id.in(menuIds))
                .fetch();

        Map<Long, List<MenuOption>> menuOptionsMap = findMenuOptionsByMenuIdIn(menuIds);
        result.forEach(menu -> {
            if (menuOptionsMap.containsKey(menu.getId())) {
                menu.initOptions(menuOptionsMap.get(menu.getId()));
            }
        });

        return result;
    }

    private Map<Long, List<MenuOption>> findMenuOptionsByMenuIdIn(List<Long> menuIds) {
        Map<MenuOption, List<MenuSelection>> optionsMap = queryFactory
                .selectFrom(menuOption)
                .leftJoin(menuOption.selections, menuSelection)
                .where(menuOption.menu.id.in(menuIds))
                .transform(groupBy(menuOption).as(list(menuSelection)));


        Map<Long, List<MenuOption>> result = new HashMap<>();
        for (MenuOption option : optionsMap.keySet()) {
            option.initSelections(optionsMap.get(option));

            List<MenuOption> value = result.getOrDefault(option.getMenuId(), new ArrayList<>());
            value.add(option);
            result.put(option.getMenuId(), value);
        }

        return result;
    }
}
