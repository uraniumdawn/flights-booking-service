package net.petrovsky.flights.util;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm");

    public static String toString(LocalDateTime localDateTime) {
        return localDateTime == null ? "" : localDateTime.format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime toDateTime(String str) {
        return StringUtils.isEmpty(str) ? LocalDateTime.now() : LocalDateTime.parse(str, DATE_TIME_FORMATTER);
    }
}
