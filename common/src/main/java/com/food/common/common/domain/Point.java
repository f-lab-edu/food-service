package com.food.common.common.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class Point {
    private final Integer amount;

    public Point(@PositiveOrZero @NotNull Integer amount) {
        this.amount = amount;
    }

    public Integer get() {
        return amount;
    }

    private boolean lessThan(Point point) {
        return this.amount < point.amount;
    }

    public Point subtract(Point point) {
        if (lessThan(point)) throw new IllegalArgumentException();

        int remainder = this.amount - point.amount;
        return new Point(remainder);
    }
}
