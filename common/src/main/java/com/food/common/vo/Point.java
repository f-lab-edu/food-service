package com.food.common.vo;

public class Point {
    private final Integer amount;

    public Point(Integer amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(Integer amount) {
        if (amount < 0) throw new IllegalArgumentException();
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
