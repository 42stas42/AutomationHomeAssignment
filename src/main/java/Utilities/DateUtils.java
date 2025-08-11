package Utilities;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

    // Patterns for relative phrases
    private static final Pattern DAYS_AGO   = Pattern.compile("(?i)^(\\d+)\\s+days?\\s+ago$");
    private static final Pattern TODAY      = Pattern.compile("(?i)^today$");
    private static final Pattern YESTERDAY  = Pattern.compile("(?i)^yesterday$");

    /**
     * Converts a relative date phrase to an absolute date string.
     * Example: "3 days ago" -> "05/August/2025"
     *
     * @param relativeDate The relative date string.
     * @param format       Desired date pattern (e.g., "dd/MMMM/yyyy").
     * @param zoneId       Time zone to use for calculation.
     * @return Absolute date string or the original string if not relative.
     */

    public static String convertRelativeToAbsolute(String relativeDate, String format, ZoneId zoneId) {
        if (relativeDate == null || relativeDate.isBlank()) {
            return relativeDate;
        }

        String trimmed = relativeDate.trim();
        LocalDate baseDate = LocalDate.now(zoneId);

        Matcher m = DAYS_AGO.matcher(trimmed);
        if (m.matches()) {
            int days = Integer.parseInt(m.group(1));
            return baseDate.minusDays(days).format(DateTimeFormatter.ofPattern(format, Locale.ENGLISH));
        }

        if (TODAY.matcher(trimmed).matches()) {
            return baseDate.format(DateTimeFormatter.ofPattern(format, Locale.ENGLISH));
        }

        if (YESTERDAY.matcher(trimmed).matches()) {
            return baseDate.minusDays(1).format(DateTimeFormatter.ofPattern(format, Locale.ENGLISH));
        }

        // If it's not a relative date, return original
        return relativeDate;
    }

    @Step("RUN method safeExtractYear")
    public static String safeExtractYear(String text) {
        try {
            Allure.step("Starting safeExtractYear with text: \"" + text + "\"");

            if (text == null || text.isEmpty()) {
                Allure.step("Input text is null or empty. Returning empty string.");
                return "";
            }

            // Match years between 1900 and 2099
            Pattern pattern = Pattern.compile("\\b(19\\d{2}|20\\d{2})\\b");
            Matcher matcher = pattern.matcher(text);

            if (matcher.find()) {
                String year = matcher.group(1);
                Allure.step("Year found: " + year);
                return year;
            }

            Allure.step("No year found. Returning ***");
            return "***";

        } catch (Exception e) {
            Allure.step("Error occurred in safeExtractYear: " + e.getMessage());
            return "***"; // returning default in case of error
        }
    }

    @Step("RUN method isValidDate")
    public static boolean isValidDate(String dateStr, String pattern, Locale locale) {
        Allure.step("Validating date string: \"" + dateStr + "\" against pattern: \"" + pattern + "\" and locale: " + locale);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale);
        try {
            LocalDate parsedDate = LocalDate.parse(dateStr, formatter);
            Allure.step("Parsed date: " + parsedDate);

            boolean matches = formatter.format(parsedDate).equals(dateStr);
            Allure.step("Formatted date back to string: \"" + formatter.format(parsedDate) + "\". Matches original: " + matches);

            return matches;
        } catch (DateTimeParseException e) {
            Allure.step("Date parsing failed for \"" + dateStr + "\": " + e.getMessage());
            return false;
        }
    }

}
