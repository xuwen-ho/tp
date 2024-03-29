package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.FOODDONATION;
import static seedu.address.testutil.TypicalAssignments.FOODDONATIONCOPY;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.PersonBuilder;

public class AssignmentTest {

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(FOODDONATION.equals(FOODDONATION));

        // null -> returns false
        assertFalse(FOODDONATION.equals(null));

        // different object but same attributes -> returns true
        assertTrue(FOODDONATION.equals(FOODDONATIONCOPY));
    }

    @Test
    public void getPersonTest() {
        Person assignedPerson = ALICE;
        assertEquals(FOODDONATION.getPerson(), assignedPerson);
    }

    @Test
    public void getAvailabilityTest() {
        Availability availability = new Availability("14/02/2024");
        assertEquals(FOODDONATION.getAvailability(),availability);
    }

    @Test
    public void getDetailsTest() {
        AssignmentDetails details = new AssignmentDetails("Food Donation");
        assertEquals(FOODDONATION.getDetails(), details.toString());
    }

    @Test
    public void toStringTest() {
        Availability availability = new Availability("14/02/2024");
        AssignmentDetails details = new AssignmentDetails("Food Donation");
        String result = new ToStringBuilder(FOODDONATION)
                .add("Volunteer", ALICE)
                .add("Date", availability)
                .add("Details", details)
                .toString();

        assertEquals(FOODDONATION.toString(),result);
    }
}
