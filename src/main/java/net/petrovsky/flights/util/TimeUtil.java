package net.petrovsky.flights.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String toString(LocalDateTime localDateTime) {
        return localDateTime == null ? "" : localDateTime.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate toDate(String str) {
        return StringUtils.isEmpty(str) ? LocalDate.now() : LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
