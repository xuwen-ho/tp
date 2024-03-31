package seedu.address.testutil;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentDetails;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Assignment Objects.
 */
public class AssignmentBuilder {

    public static final String DEFAULT_AVAILABILITY = "01/01/2024";
    public static final String DEFAULT_DETAILS = "Food Bank";

    private Person person;
    private AssignmentDetails details;
    private Availability availability;

    /**
     * Creates a {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        this.person = new PersonBuilder().build();
        this.details = new AssignmentDetails(DEFAULT_DETAILS);
        this.availability = new Availability(DEFAULT_AVAILABILITY);
    }

    /**
     * Sets the {@code details} of the {@code Assignment} that we are building
     * @param assignmentDetails
     * @return
     */
    public AssignmentBuilder withDetails(String assignmentDetails) {
        details = new AssignmentDetails(assignmentDetails);
        return this;
    }


    /**
     * Sets the {@code availability} of the {@code Assignment} that we are building
     */
    public AssignmentBuilder withAvailability(String availability) {
        this.availability = new Availability(availability);
        return this;
    }

    /**
     * Sets the {@code person} of the {@code Assignment} that we are building
     */
    public AssignmentBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }


    /**
     * Builds an assignment object
     * @return Assignment
     */
    public Assignment build() {
        return new Assignment(person, details, availability);
    }
}
