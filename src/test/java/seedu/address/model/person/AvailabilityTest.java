package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AvailabilityTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Availability(null));
    }

    @Test
    public void constructor_invalidAvailability_throwsIllegalArgumentException() {
        String invalidAvailability = "";
        assertThrows(IllegalArgumentException.class, () -> new Availability(invalidAvailability));
    }

    @Test
    public void isValidDateFormat() {
        // null date format
        assertThrows(NullPointerException.class, () -> Availability.isValidDateFormat(null));

        // invalid date format
        assertFalse(Availability.isValidDateFormat("")); // empty string
        assertFalse(Availability.isValidDateFormat(" ")); // spaces only
        assertFalse(Availability.isValidDateFormat("^")); // only non-alphanumeric characters
        assertFalse(Availability.isValidDateFormat("01/01/24")); // invalid date format
        assertFalse(Availability.isValidDateFormat("1/01/2024")); // invalid date format
        assertFalse(Availability.isValidDateFormat("01/1/2024")); // invalid date format
        assertFalse(Availability.isValidDateFormat("01-01-2024")); // invalid date format

        // valid date format
        assertTrue(Availability.isValidDateFormat("01/01/2024")); // date format
    }

    @Test
    public void isValidAvailability() {
        // null availability
        assertThrows(NullPointerException.class, () -> Availability.isValidAvailability(null));

        // invalid availability
        assertFalse(Availability.isValidAvailability("")); // empty string
        assertFalse(Availability.isValidAvailability(" ")); // spaces only
        assertFalse(Availability.isValidAvailability("^")); // only non-alphanumeric characters
        assertFalse(Availability.isValidAvailability("peter*")); // contains non-alphanumeric characters
        assertFalse(Availability.isValidAvailability("Available")); // alphabets only

        // valid availability
        assertTrue(Availability.isValidAvailability("01/01/2024")); // date format
    }

    @Test
    public void equals() {
        Availability availability = new Availability("01/01/2024");

        // same values -> returns true
        assertTrue(availability.equals(new Availability("01/01/2024")));

        // same object -> returns true
        assertTrue(availability.equals(availability));

        // null -> returns false
        assertFalse(availability.equals(null));

        // different types -> returns false
        assertFalse(availability.equals(5.0f));

        // different values -> returns false
        assertFalse(availability.equals(new Availability("12/12/2024")));
    }
}
