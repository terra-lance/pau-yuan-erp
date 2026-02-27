package com.terrase.util;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	// dd-MM-yyyy Format
	private static final String DATE_PATTERN = "^(0[1-9]|1[0-9]|2[0-8]|29((?=-([0][13-9]|1[0-2])|(?=-(0[1-9]|1[0-2])-([0-9]{2}(0[48]|[13579][26]|[2468][048])|([02468][048]|[13579][26])00))))|30(?=-(0[13-9]|1[0-2]))|31(?=-(0[13578]|1[02])))-(0[1-9]|1[0-2])-[0-9]{4}$";

	public static long validDay(Date dateStart, Date dateEnd, boolean sunday, boolean monday, boolean tuesday,
			boolean wednesday, boolean thursday, boolean friday, boolean saturday) {
		long days = 0;

		do {
			if ((DateUtil.isSunday(dateStart) && sunday) || (DateUtil.isMonday(dateStart) && monday)
					|| (DateUtil.isTuesday(dateStart) && tuesday) || (DateUtil.isWednesday(dateStart) && wednesday)
					|| (DateUtil.isThursday(dateStart) && thursday) || (DateUtil.isFriday(dateStart) && friday)
					|| (DateUtil.isSaturday(dateStart) && saturday)) {
				days++;
			}

			dateStart = DateUtil.addDate(dateStart, 1);
		} while (dateStart.before(DateUtil.addDate(dateEnd, 1)));

		return days;
	}

	public static long hourDifference(Date dateStart, Date dateEnd) {
		Instant startInstant = dateStart.toInstant();
		ZonedDateTime startDate = startInstant.atZone(ZoneId.systemDefault());

		Instant endInstant = dateEnd.toInstant();
		ZonedDateTime endDate = endInstant.atZone(ZoneId.systemDefault());

		return ChronoUnit.HOURS.between(startDate, endDate);
	}

	public static long dayDifference(Date dateStart, Date dateEnd) {
		Instant startInstant = dateStart.toInstant();
		ZonedDateTime startDate = startInstant.atZone(ZoneId.systemDefault());

		Instant endInstant = dateEnd.toInstant();
		ZonedDateTime endDate = endInstant.atZone(ZoneId.systemDefault());

		return ChronoUnit.DAYS.between(startDate, endDate);
	}

	public static long yearDifference(Date dateStart, Date dateEnd) {
		Instant startInstant = dateStart.toInstant();
		ZonedDateTime startDate = startInstant.atZone(ZoneId.systemDefault());

		Instant endInstant = dateEnd.toInstant();
		ZonedDateTime endDate = endInstant.atZone(ZoneId.systemDefault());

		return ChronoUnit.YEARS.between(startDate, endDate);
	}

	public static boolean validateDatePattern(String date) {
		boolean valid = true;

		if (date.length() != 8) {
			valid = false;
		} else {
			String dayString = date.substring(0, 2);
			String monthString = date.substring(2, 4);
			String yearString = date.substring(4, 8);
			date = dayString + "-" + monthString + "-" + yearString;

			Pattern pattern = Pattern.compile(DATE_PATTERN);
			Matcher matcher = pattern.matcher(date);
			valid = matcher.matches();
		}

		return valid;
	}

	public static Date toStartOfDay(Date date) {
		if (date != null) {
			Instant dateInstant = date.toInstant();
			ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
			LocalDateTime localDateTime = dateZdt.toLocalDate().atStartOfDay();
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		} else {
			return null;
		}
	}

	public static Date toEndOfDay(Date date) {
		if (date != null) {
			Instant dateInstant = date.toInstant();
			ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
			LocalDateTime localDateTime = dateZdt.toLocalDate().plusDays(1).atStartOfDay().minusNanos(1);
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		} else {
			return null;
		}
	}

	public static Date toStartOfMonth(Date date) {
		if (date != null) {
			Instant dateInstant = date.toInstant();
			ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
			LocalDateTime localDateTime = dateZdt.toLocalDate().withDayOfMonth(1).atStartOfDay();
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		} else {
			return null;
		}
	}

	public static int day(Date date) {
		if (date != null) {
			Instant dateInstant = date.toInstant();
			ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
			LocalDate localDate = dateZdt.toLocalDate();
			return localDate.getDayOfMonth();
		} else {
			return 0;
		}
	}

	public static int month(Date date) {
		if (date != null) {
			Instant dateInstant = date.toInstant();
			ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
			LocalDate localDate = dateZdt.toLocalDate();
			return localDate.getMonthValue();
		} else {
			return 0;
		}
	}

	public static int year(Date date) {
		if (date != null) {
			Instant dateInstant = date.toInstant();
			ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
			LocalDate localDate = dateZdt.toLocalDate();
			return localDate.getYear();
		} else {
			return 0;
		}
	}

	public static Date changeDay(Date date, int day) {
		if (date != null) {
			Instant dateInstant = date.toInstant();
			ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
			LocalDateTime localDateTime = dateZdt.toLocalDateTime().withDayOfMonth(day);
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		} else {
			return null;
		}
	}

	public static Date changeMonth(Date date, int month) {
		if (date != null) {
			Instant dateInstant = date.toInstant();
			ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
			LocalDateTime localDateTime = dateZdt.toLocalDateTime().withMonth(month);
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		} else {
			return null;
		}
	}

	public static Date changeYear(Date date, int year) {
		if (date != null) {
			Instant dateInstant = date.toInstant();
			ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
			LocalDateTime localDateTime = dateZdt.toLocalDateTime().withYear(year);
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		} else {
			return null;
		}
	}

	public static Date addNano(Date date, int nano) {
		if (date != null) {
			Instant dateInstant = date.toInstant().plus(nano, ChronoUnit.NANOS);
			return Date.from(dateInstant);
		} else {
			return null;
		}
	}

	public static Date addMinute(Date date, int minute) {
		if (date != null) {
			Instant dateInstant = date.toInstant().plus(minute, ChronoUnit.MINUTES);
			return Date.from(dateInstant);
		} else {
			return null;
		}
	}

	public static Date addHour(Date date, int hour) {
		if (date != null) {
			Instant dateInstant = date.toInstant().plus(hour, ChronoUnit.HOURS);
			return Date.from(dateInstant);
		} else {
			return null;
		}
	}

	public static Date addDate(Date date, int day) {
		if (date != null) {
			Instant dateInstant = date.toInstant().plus(day, ChronoUnit.DAYS);
			return Date.from(dateInstant);
		} else {
			return null;
		}
	}

	public static Date addMonth(Date date, int month) {
		if (date != null) {
			Instant dateInstant = date.toInstant().plus(month, ChronoUnit.MONTHS);
			return Date.from(dateInstant);
		} else {
			return null;
		}
	}

	public static Date addYear(Date date, int year) {
		if (date != null) {
			Instant dateInstant = date.toInstant().plus(year, ChronoUnit.YEARS);
			return Date.from(dateInstant);
		} else {
			return null;
		}
	}

	public static Date combineDateTime(Date date, Date time) {
		if (date != null && time != null) {
			LocalDate localDate = date.toInstant().atZone(ZoneOffset.systemDefault()).toLocalDate();
			LocalTime localTime = time.toInstant().atZone(ZoneOffset.systemDefault()).toLocalTime();
			return Date.from(localDate.atTime(localTime).atZone(ZoneId.systemDefault()).toInstant());
		} else {
			return null;
		}
	}

	public static Date today() {
		Date date = new Date();
		Instant dateInstant = date.toInstant();
		ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
		LocalDateTime localDateTime = dateZdt.toLocalDate().atStartOfDay();
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static int todayDate() {
		Date date = new Date();
		Instant dateInstant = date.toInstant();
		ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
		LocalDateTime localDateTime = dateZdt.toLocalDate().atStartOfDay();
		return localDateTime.getDayOfMonth();
	}

	public static int todayMonth() {
		Date date = new Date();
		Instant dateInstant = date.toInstant();
		ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
		LocalDateTime localDateTime = dateZdt.toLocalDate().atStartOfDay();
		return localDateTime.getMonthValue();
	}

	public static Date toDate(String dateString, String format) {
		int dayIndex = format.indexOf("dd");
		int monthIndex = format.indexOf("MM");
		int yearIndex = format.indexOf("yyyy");

		String dayString = dateString.substring(dayIndex, dayIndex + 2);
		String monthString = dateString.substring(monthIndex, monthIndex + 2);
		String yearString = dateString.substring(yearIndex, yearIndex + 4);

		Date date = new Date();
		Instant dateInstant = date.toInstant();
		ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
		LocalDate localDate = dateZdt.toLocalDate();
		localDate = localDate.withYear(Integer.valueOf(yearString));
		localDate = localDate.withMonth(Integer.valueOf(monthString));
		localDate = localDate.withDayOfMonth(Integer.valueOf(dayString));

		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static boolean isSunday(Date date) {
		Instant dateInstant = date.toInstant();
		ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
		LocalDate localDate = dateZdt.toLocalDate();
		return localDate.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	public static boolean isMonday(Date date) {
		Instant dateInstant = date.toInstant();
		ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
		LocalDate localDate = dateZdt.toLocalDate();
		return localDate.getDayOfWeek() == DayOfWeek.MONDAY;
	}

	public static boolean isTuesday(Date date) {
		Instant dateInstant = date.toInstant();
		ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
		LocalDate localDate = dateZdt.toLocalDate();
		return localDate.getDayOfWeek() == DayOfWeek.TUESDAY;
	}

	public static boolean isWednesday(Date date) {
		Instant dateInstant = date.toInstant();
		ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
		LocalDate localDate = dateZdt.toLocalDate();
		return localDate.getDayOfWeek() == DayOfWeek.WEDNESDAY;
	}

	public static boolean isThursday(Date date) {
		Instant dateInstant = date.toInstant();
		ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
		LocalDate localDate = dateZdt.toLocalDate();
		return localDate.getDayOfWeek() == DayOfWeek.THURSDAY;
	}

	public static boolean isFriday(Date date) {
		Instant dateInstant = date.toInstant();
		ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
		LocalDate localDate = dateZdt.toLocalDate();
		return localDate.getDayOfWeek() == DayOfWeek.FRIDAY;
	}

	public static boolean isSaturday(Date date) {
		Instant dateInstant = date.toInstant();
		ZonedDateTime dateZdt = dateInstant.atZone(ZoneId.systemDefault());
		LocalDate localDate = dateZdt.toLocalDate();
		return localDate.getDayOfWeek() == DayOfWeek.SATURDAY;
	}

	public static String toString(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	public static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDateTime toLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}
