package com.food.auth.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(
                localDateTime.atZone(ZoneId.systemDefault()).toInstant()
        );
    }
}
