package seedu.address.model.assignment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

/**
 * Represents an Assignment in the application.
 * Guarantees: N/A
 */
public class Assignment {

    private final Person assignedPerson;
    private final AssignmentDetails details;
    private final Availability availability;

    /**
     * Creates an assignment with the given parameters.
     * @param assignedPerson
     * @param details
     * @param availability
     */
    public Assignment(Person assignedPerson, AssignmentDetails details, Availability availability) {
        requireAllNonNull(assignedPerson, details, availability);
        this.assignedPerson = assignedPerson;
        this.details = details;
        this.availability = availability;
    }

    public Person getPerson() {
        return assignedPerson;
    }

    public Availability getAvailability() {
        return availability;
    }

    public String getDetails() {
        return details.toString();
    }


    /**
     * Returns true if both assignments have the same identity and data fields.
     * This defines a stronger notion of equality between two assignments
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return assignedPerson.equals(otherAssignment.assignedPerson)
                && details.equals(otherAssignment.details)
                && availability.equals(otherAssignment.availability);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(assignedPerson, details, availability);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Volunteer", assignedPerson)
                .add("Date", availability)
                .add("Details", details)
                .toString();
    }
}
