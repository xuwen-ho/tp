package seedu.address.testutil;


import static seedu.address.testutil.TypicalPersons.ALICE;
import seedu.address.model.assignment.Assignment;

/**
 * A utility class containing a list {@code Assignment} objects to be used in tests.
 */
public class TypicalAssignments {

    public static final Assignment FOODDONATION = new AssignmentBuilder().withPerson(ALICE)
            .withDetails("Food Donation")
            .withAvailability("01/01/2024").build();
}
