package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

/**
 * Represents the details of the assignment
 */
public class AssignmentDetails {
    private String details;

    /**
     * Creates an assignment object based on the given details.
     * Detail needs to be non-null.
     */
    public AssignmentDetails(String details) {
        requireNonNull(details);
        this.details = details;
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
