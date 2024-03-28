package seedu.address.model.assignment;

import java.util.Objects;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

public class Assignment {
    // string to store assignment details
    // Person assigned
    // date of assignment
    // show phone
    // show email
//    private final int test;
    private final Person assignedPerson;
    private final AssignmentDetails details;
    private final Availability availability;

    public Assignment(Person assignedPerson, AssignmentDetails details, Availability availability) {
        this.assignedPerson = assignedPerson;
        this.details = details;
        this.availability = availability;
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
        return assignedPerson.equals(((Assignment) other).assignedPerson)
                && details.equals(((Assignment) other).details)
                && availability.equals((((Assignment) other).availability));
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
