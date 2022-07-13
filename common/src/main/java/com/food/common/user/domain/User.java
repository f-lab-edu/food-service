package com.food.common.user.domain;

import com.food.common.common.domain.BaseTimeEntity;
import com.food.common.common.domain.Point;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_user")
@Entity
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    private String id;

    private String password;

    private String nickname;

    private Integer point;

    public User(String id, String password, String nickname, Point point) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.point = point.get();
    }

    public Point use(Point point) {
        Point currentPoint = getPoint().subtract(point);
        this.point = currentPoint.get();

        return currentPoint;
    }

    public Point getPoint() {
        return new Point(point);
    }

    public Long getUserId() {
        return userId;
    }
}
