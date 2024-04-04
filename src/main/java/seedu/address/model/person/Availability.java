package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Represents a Person's availability in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAvailability(String)}
 */
public class Availability {
    public static final String MESSAGE_CONSTRAINTS = "Availabilities must be in the format of dd/MM/yyyy";
    public static final String MESSAGE_CONSTRAINTS_DATE = "Availabilities must be a valid date";

    /**
     * The first character of the availability must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "dd/MM/yyyy";
    public static final String VALIDATION_REGEX_DATE = "\\d{2}/\\d{2}/\\d{4}";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(VALIDATION_REGEX);

    private LocalDate date;

    /**
     * Constructs an {@code Address}.
     *
     * @param availability A valid date.
     */
    public Availability(String availability) {
        requireNonNull(availability);
        checkArgument(isValidDateFormat(availability), MESSAGE_CONSTRAINTS);
        checkArgument(isValidAvailability(availability), MESSAGE_CONSTRAINTS_DATE);
        this.date = LocalDate.parse(availability, formatter);
    }

    /**
     * Returns true if a given string is a valid date format.
     */
    public static boolean isValidDateFormat(String test) {
        return Pattern.matches(VALIDATION_REGEX_DATE, test);
    }

    /**
     * Returns true if a given string is a valid availability.
     */
    public static boolean isValidAvailability(String test) {
        test = test.trim();
        String result;
        try {
            result = LocalDate.parse(test, formatter).format(formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return result.equals(test);
    }

    /**
     * Returns the date of availability.
     *
     * @return The date of availability.
     */
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Availability)) {
            return false;
        }

        Availability otherAvailability = (Availability) other;
        return date.equals(otherAvailability.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
