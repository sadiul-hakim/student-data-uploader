package com.hakim.datauploder.util;

import com.hakim.datauploder.enumeration.Grain;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;
import java.util.logging.Level;

public final class DateTimeUtility {
    private DateTimeUtility() {
    }

    public static Optional<LocalDate> formatDate(String dateText, String format) {
        try {
            var formatter = DateTimeFormatter.ofPattern(format);
            return Optional.of(LocalDate.parse(dateText, formatter));
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, ex.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<LocalDateTime> formatDateTime(String dateText, String format) {
        try {
            var formatter = DateTimeFormatter.ofPattern(format);
            return Optional.of(LocalDateTime.parse(dateText, formatter));
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, ex.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<ZonedDateTime> formatZonedDateTime(String dateText, String format) {
        try {
            var formatter = DateTimeFormatter.ofPattern(format);
            return Optional.of(ZonedDateTime.parse(dateText, formatter));
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, ex.getMessage());
            return Optional.empty();
        }
    }

    public static String formatZonedDateTime(ZonedDateTime dateText, String format) {
        try {
            var formatter = DateTimeFormatter.ofPattern(format);
            return formatter.format(dateText);
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, ex.getMessage());
            return "";
        }
    }

    public static Optional<OffsetDateTime> formatOffsetDateTime(String dateText, String format) {
        try {
            var formatter = DateTimeFormatter.ofPattern(format);
            return Optional.of(OffsetDateTime.parse(dateText, formatter));
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, ex.getMessage());
            return Optional.empty();
        }
    }

    public static ZonedDateTime convertToDifferentZoneDateTime(ZonedDateTime dateTime, String zoneId) {
        try {
            ZoneId zone = ZoneId.of(zoneId);
            return dateTime.withZoneSameInstant(zone);
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, ex.getMessage());
            return dateTime;
        }
    }

    public static OffsetDateTime convertToDifferentOffsetDateTime(OffsetDateTime dateTime, String offset) {
        try {
            return dateTime.withOffsetSameInstant(ZoneOffset.of(offset));
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, ex.getMessage());
            return dateTime;
        }
    }

    public static ZonedDateTime nextGrainDate(ZonedDateTime dateTime, Grain grain) {

        ZonedDateTime newDateTine;
        switch (grain) {
            case MINUTE -> newDateTine = dateTime.plusMinutes(1);
            case FIVE_MINUTE -> {
                return dateTime.plusMinutes(5 - (dateTime.getMinute() % 5))
                        .withSecond(0)
                        .withNano(0);
            }
            case QUARTER_HOURLY -> {
                return dateTime.plusMinutes(15 - (dateTime.getMinute() % 15))
                        .withSecond(0)
                        .withNano(0);
            }
            case HOURLY -> newDateTine = dateTime.plusHours(1);
            case DAILY -> newDateTine = dateTime.plusDays(1);
            case WEEKLY -> {
                return dateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                        .withHour(0)
                        .withMinute(0)
                        .withSecond(0)
                        .withNano(0);
            }
            case MONTHLY -> newDateTine = dateTime.plusMonths(1);
            case QUARTERLY -> newDateTine = dateTime.plusMonths(3);
            case YEARLY -> newDateTine = dateTime.plusYears(1);
            default -> newDateTine = dateTime;
        }
        return toBeginningOfThePeriod(newDateTine, grain);
    }

    public static ZonedDateTime toBeginningOfThePeriod(ZonedDateTime dateTime, Grain grain) {

        switch (grain) {
            case MINUTE -> {
                return dateTime.truncatedTo(ChronoUnit.MINUTES);
            }
            case HOURLY -> {
                return dateTime.truncatedTo(ChronoUnit.HOURS);
            }
            case DAILY -> {
                return dateTime.truncatedTo(ChronoUnit.DAYS);
            }
            case MONTHLY -> {
                return ZonedDateTime.of(dateTime.getYear(), dateTime.getMonthValue(), 1,
                        0, 0, 0, 0, dateTime.getZone());
            }
            case QUARTERLY -> {
                int quarterMonth = dateTime.getMonthValue();
                int month;
                if (quarterMonth < 3) {
                    month = 0;
                } else if (quarterMonth < 6) {
                    month = 3;
                } else if (quarterMonth < 9) {
                    month = 6;
                } else {
                    month = 9;
                }
                return ZonedDateTime.of(dateTime.getYear(), month, 1,
                        0, 0, 0, 0, dateTime.getZone());
            }
            case YEARLY -> {
                return ZonedDateTime.of(dateTime.getYear(), 1, 1,
                        0, 0, 0, 0, dateTime.getZone());
            }
            default -> {
                return dateTime;
            }
        }
    }
}