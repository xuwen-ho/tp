package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentDetails;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;


public class JsonAdaptedAssignment {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";

    private final JsonAdaptedPerson person;
    private final String details;
    private JsonAdaptedAvailability availability;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given assignment details.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("person") JsonAdaptedPerson person,
                                 @JsonProperty("details") String details,
                                 @JsonProperty("availability") JsonAdaptedAvailability availability) {
        this.person = person;
        this.details = details;
        this.availability = availability;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        person = new JsonAdaptedPerson(source.getPerson());
        details = source.getDetails();
        availability = new JsonAdaptedAvailability(source.getAvailability());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Assignment toModelType() throws IllegalValueException {
        final Person modelPerson = person.toModelType();
        final Availability modelAvailability = availability.toModelType();
        final AssignmentDetails modelDetails = new AssignmentDetails(details);

        if (modelPerson == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Person.class.getSimpleName()));
        }

        if (modelAvailability == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Availability.class.getSimpleName()));
        }

        if (modelDetails == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AssignmentDetails.class.getSimpleName()));
        }

        return new Assignment(modelPerson, modelDetails, modelAvailability);
    }
}
