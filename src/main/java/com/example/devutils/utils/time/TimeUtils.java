package com.example.devutils.utils.time;

import com.example.devutils.dep.Zones;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * Created by AMe on 2020-06-18 12:58.
 */
public class TimeUtils {

    public static Date getNowDate() {
        return new Date();
    }

    public static long getNowTimestamp() {
        return System.currentTimeMillis();
    }

    public static LocalDateTime getNowLocalDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate getNowLocalDate() {
        return LocalDate.now();
    }

    public static LocalTime getNowLocalTime() {
        return LocalTime.now();
    }

    public static YearMonth getNowYearMonth() {
        return YearMonth.now();
    }

    public static Instant getNowInstant() {
        return Instant.now();
    }

    public static Date getDate(long timestamp) {
        return new Date(timestamp);
    }

    public static LocalDateTime getLocalDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
    }

    public static LocalDate getLocalDate(int year, int month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth);
    }

    public static LocalTime getLocalTime(int hour, int minute, int second) {
        return LocalTime.of(hour, minute, second);
    }

    public static YearMonth getYearMonth(int year, int month) {
        return YearMonth.of(year, month);
    }

    public static Instant getInstant(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli);
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return toDate(toInstant(localDateTime, Zones.SYSTEM_ZONE));
    }

    public static Date toDate(Instant instant) {
        return Date.from(instant);
    }

    public static long toTimestamp(Date date) {
        return date.getTime();
    }

    public static long toTimestamp(LocalDateTime localDateTime) {
        return toTimestamp(toInstant(localDateTime));
    }

    public static long toTimestamp(Instant instant) {
        return instant.toEpochMilli();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return toLocalDateTime(toInstant(date), Zones.SYSTEM_ZONE);
    }

    public static LocalDateTime toLocalDateTime(Instant instant) {
        return toLocalDateTime(instant, Zones.DEFAULT_ZONE);
    }

    public static LocalDateTime toLocalDateTime(Instant instant, ZoneId zoneId) {
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    public static Instant toInstant(Date date) {
        return date.toInstant();
    }

    public static Instant toInstant(LocalDateTime localDateTime) {
        return toInstant(localDateTime, Zones.DEFAULT_ZONE);
    }

    public static Instant toInstant(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId).toInstant();
    }

    public static LocalDateTime plus(LocalDateTime localDateTime, int amountToAdd, TemporalUnit unit) {
        return localDateTime.plus(amountToAdd, unit);
    }

    public static LocalDateTime adjustToYear(LocalDateTime localDateTime, int year) {
        return localDateTime.withYear(year);
    }

    public static LocalDateTime adjustToMonthOfYear(LocalDateTime localDateTime, int month) {
        return localDateTime.withMonth(month);
    }

    public static LocalDateTime adjustToDayOfMonth(LocalDateTime localDateTime, int dayOfMonth) {
        return localDateTime.withDayOfMonth(dayOfMonth);
    }

    public static LocalDateTime adjustToLastDayOfMonth(LocalDateTime localDateTime) {
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDateTime adjustTimeZone(LocalDateTime srcDateTime, ZoneId srcZoneId, ZoneId targetZoneId) {
        return toLocalDateTime(toInstant(srcDateTime, srcZoneId), targetZoneId);
    }

    public static Date parseToDate(String dateStr, String pattern) throws ParseException {
        return TimeFormatterUtils.getSimpleDateFormat(pattern).parse(dateStr);
    }

    public static LocalDateTime parseToLocalDateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, TimeFormatterUtils.getDateTimeFormatter(pattern));
    }

    public static Instant parseToInstant(String instantStr, String pattern) {
        return ZonedDateTime.parse(instantStr, TimeFormatterUtils.getDateTimeFormatter(pattern)).toInstant();
    }

    public static String formatDate(Date date, String pattern) {
        return TimeFormatterUtils.getSimpleDateFormat(pattern).format(date);
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(TimeFormatterUtils.getDateTimeFormatter(pattern));
    }

    public static String formatInstant(Instant instant, String pattern) {
        return formatInstant(instant, pattern, Zones.DEFAULT_ZONE);
    }

    public static String formatInstant(Instant instant, String pattern, ZoneId zoneId) {
        return TimeFormatterUtils.getDateTimeFormatter(pattern).withZone(zoneId).format(instant);
    }

    public static long betweenDays(LocalDateTime startInclusive, LocalDateTime endExclusive) {
        return Duration.between(startInclusive, endExclusive).toDays();
    }

    public static long betweenHours(LocalDateTime startInclusive, LocalDateTime endExclusive) {
        return Duration.between(startInclusive, endExclusive).toHours();
    }

    public static long betweenMinutes(LocalDateTime startInclusive, LocalDateTime endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMinutes();
    }

}