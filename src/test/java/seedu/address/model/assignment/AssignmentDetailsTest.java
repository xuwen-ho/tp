package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AssignmentDetailsTest {

    @Test
    public void equals() {
        AssignmentDetails details = new AssignmentDetails("Food Bank");
        AssignmentDetails detailsCopy = new AssignmentDetails("Food Bank");
        assertTrue(details.equals(detailsCopy));

        assertTrue(details.equals(details));

        assertFalse(details.equals(null));
    }
}
