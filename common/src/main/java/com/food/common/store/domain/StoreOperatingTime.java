package com.food.common.store.domain;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalTime;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_store_operating_time")
@Entity
public class StoreOperatingTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "store_operating_time_id")
    private Long id;

    @Comment("가게 고유번호")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Comment("영업 요일")
    @NotNull
    private Week week;

    @Comment("영업 시작일시")
    @NotNull
    private LocalTime openingTime;

    @Comment("영업 종료일시")
    @NotNull
    private LocalTime closingTime;

    public static StoreOperatingTime create(Store store, Week week, LocalTime openingTime, LocalTime closingTime) {
        StoreOperatingTime operatingTime = new StoreOperatingTime();
        operatingTime.store = store;
        operatingTime.week = week;
        operatingTime.openingTime = openingTime;
        operatingTime.closingTime = closingTime;

        return operatingTime;
    }

    public enum Week {
        MON("월요일"),
        TUE("화요일"),
        WED("수요일"),
        THU("목요일"),
        FRI("금요일"),
        SAT("토요일"),
        SUN("일요일")
        ;

        private final String description;

        Week(String description) {
            this.description = description;
        }
    }
}
