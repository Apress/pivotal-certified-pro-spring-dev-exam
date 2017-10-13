package com.ps.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by iuliana.cosmina on 2/7/16.
 */
public class DateFormatter implements Formatter<Date> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        LocalDateTime localDateTime = LocalDate.from(FORMATTER.parse(s)).atStartOfDay();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    @Override
    public String print(Date date, Locale locale) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return FORMATTER.format(localDateTime);
    }
}