package seedu.address.storage;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DETAILS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AVAILABILITY_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;


public class JsonAdaptedAssignmentTest {

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final List<JsonAdaptedAvailability> VALID_AVAILABILITY = BENSON.getAvailabilities().stream()
            .map(JsonAdaptedAvailability::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_nullPerson_throwsNullPointerException() {
        JsonAdaptedAvailabilityStub availabilityStub = new JsonAdaptedAvailabilityStub();
        JsonAdaptedAssignment assignment =
                new JsonAdaptedAssignment(null, VALID_ASSIGNMENT_DETAILS, availabilityStub);
        // person cannot be null
        assertThrows(NullPointerException.class, assignment::toModelType);
    }

    @Test
    public void toModelType_nullAvailability_throwsNullPointerException() {
        JsonAdaptedPersonStub personStub = new JsonAdaptedPersonStub();
        JsonAdaptedAssignment assignment =
                new JsonAdaptedAssignment(personStub, VALID_ASSIGNMENT_DETAILS, null);
        // person cannot be null
        assertThrows(NullPointerException.class, assignment::toModelType);
    }

    @Test
    public void toModelType_nullDetails_throwsNullPointerException() {
        JsonAdaptedPersonStub personStub = new JsonAdaptedPersonStub();
        JsonAdaptedAvailabilityStub availabilityStub = new JsonAdaptedAvailabilityStub();
        JsonAdaptedAssignment assignment =
                new JsonAdaptedAssignment(personStub, null, availabilityStub);
        // person cannot be null
        assertThrows(NullPointerException.class, assignment::toModelType);
    }

    private class JsonAdaptedPersonStub extends JsonAdaptedPerson {
        private JsonAdaptedPersonStub() {
            super(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_AVAILABILITY, VALID_TAGS);
        }

    }

    private class JsonAdaptedAvailabilityStub extends JsonAdaptedAvailability {
        private JsonAdaptedAvailabilityStub() {
            super(VALID_AVAILABILITY_AMY);
        }
    }
}
