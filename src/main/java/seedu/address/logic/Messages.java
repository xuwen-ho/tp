package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;


/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The volunteer index provided is invalid";
    public static final String MESSAGES_INVALID_ASSIGNMENT_DISPLAYED_INDEX = "The assignment index provided"
            + " is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d volunteer(s) found!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_CONFIRMATION = "Are you sure that you want to proceed with that action? "
            + "[y/N]";

    public static final String MESSAGE_CONFIRMATION_CANCELLED = "Command execution has been cancelled.";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Availabilities: ");
        person.getAvailabilities().forEach(builder::append);
        builder.append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code assignment} fpr display to the user.
     * @param assignment The person whose availabilities are to be formatted.
     * @return A string representation of the person's assignments.
     */
    public static String format(Assignment assignment) {
        final StringBuilder builder = new StringBuilder();
        builder.append(assignment.getPerson().getName().fullName)
            .append(" assigned to ")
            .append(assignment.getDetails()).append(" on ")
            .append(assignment.getAvailability());
        return builder.toString();
    }

    /**
     * Formats the availabilities of the {@code person} for display to the user.
     * @param person The person whose availabilities are to be formatted.
     * @return A string representation of the person's availabilities.
     */
    public static String formatAvailability(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
            .append("; Availabilities: ");
        String formattedAvailabilities = person.getAvailabilities().stream()
            .map(Availability::toString)
            .collect(Collectors.joining(", "));

        builder.append(formattedAvailabilities);

        return builder.toString();
    }
}
