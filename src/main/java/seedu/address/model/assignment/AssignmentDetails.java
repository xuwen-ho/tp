package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

/**
 * Represents the details of the assignment
 */
public class AssignmentDetails {

    public static final String MESSAGE_CONSTRAINTS = "Assignment Details must be alpha-numeric and not empty.";
    /*
     * The first character of the string must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private String details;

    /**
     * Creates an assignment object based on the given details.
     * Detail needs to be non-null.
     */
    public AssignmentDetails(String details) {
        requireNonNull(details);
        this.details = details;
    }

    /**
     * Returns true if given string is a valid Assignment Details.
     */
    public static boolean isValidAssignmentDetails(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return details;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignmentDetails)) {
            return false;
        }

        AssignmentDetails otherAssignmentDetails = (AssignmentDetails) other;
        return details.equals(otherAssignmentDetails.details);
    }
}
