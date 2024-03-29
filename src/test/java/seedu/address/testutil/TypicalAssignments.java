package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.assignment.Assignment;

/**
 * A utility class containing a list {@code Assignment} objects to be used in tests.
 */
public class TypicalAssignments {

    public static final Assignment FOODDONATION = new AssignmentBuilder().withPerson(ALICE)
            .withDetails("Food Donation")
            .withAvailability("14/02/2024").build();

    public static final Assignment FOODDONATIONCOPY = new AssignmentBuilder().withPerson(ALICE)
            .withDetails("Food Donation")
            .withAvailability("14/02/2024").build();

    public static final Assignment TUTORING = new AssignmentBuilder().withPerson(BENSON)
            .withDetails("Tutoring")
            .withAvailability("20/03/2024").build();

    public static final Assignment ELDERLYCARE = new AssignmentBuilder().withPerson(CARL)
            .withDetails("Elderly Care")
            .withAvailability("12/12/2024").build();


    private TypicalAssignments() {} // prevents instantiation

    /**
     * Returns a list of typical assignments
     */
    public static List<Assignment> getTypicalAssignments() {
        return new ArrayList<>(Arrays.asList(FOODDONATION, FOODDONATIONCOPY, TUTORING, ELDERLYCARE));
    }
}
