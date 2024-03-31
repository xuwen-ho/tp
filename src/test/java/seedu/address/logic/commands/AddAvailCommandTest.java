package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;


public class AddAvailCommandTest {

    private final Set<Availability> validAvailabilities = new HashSet<>() {{
                add(new Availability("20/04/2024"));
                add(new Availability("21/04/2024"));
        }
    };

    private final Set<Availability> validAvailabilitiesSecond = new HashSet<>() {{
                add(new Availability("22/04/2024"));
                add(new Availability("23/04/2024"));
        }
    };

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAvailCommand(null, validAvailabilities));
    }

    @Test
    public void constructor_nullAvailabilities_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAvailCommand(Index.fromZeroBased(1),
            null));
    }

    @Test
    public void constructor_nullIndexAvailabilities_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAvailCommand(null, null));
    }

    @Test
    public void execute_validInput_success() {
        AddAvailCommand addAvailCommand = new AddAvailCommand(INDEX_FIRST_PERSON, validAvailabilities);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person personToEdit = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Availability> expectedAvailabilities = new HashSet<>(personToEdit.getAvailabilities());
        expectedAvailabilities.addAll(validAvailabilities);
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
            expectedAvailabilities, personToEdit.getTags());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(addAvailCommand, model, String.format(AddAvailCommand.MESSAGE_ADD_AVAILABILITY_SUCCESS,
                Messages.formatAvailability(editedPerson)),
            expectedModel);
    }

    @Test
    public void execute_addDuplicateAvailability_throwsCommandException() throws CommandException {
        // Create an index for the person
        Index validIndex = Index.fromZeroBased(1);

        // Create an AddAvailCommand with the index and a valid availability
        AddAvailCommand addAvailCommand = new AddAvailCommand(validIndex, validAvailabilities);

        // Execute the command to add the availability for the first time
        CommandResult result = addAvailCommand.execute(model);

        // Retrieve the person after adding the availability
        Person editedPerson = model.getFilteredPersonList().get(validIndex.getZeroBased());

        // Verify the success message and the added availability
        String expectedSuccessMessage = String.format(AddAvailCommand.MESSAGE_ADD_AVAILABILITY_SUCCESS,
            Messages.formatAvailability(editedPerson));
        assertEquals(expectedSuccessMessage, result.getFeedbackToUser());

        // Ensure that the availability was added successfully
        Set<Availability> expectedAvailabilities = new HashSet<>(editedPerson.getAvailabilities());
        expectedAvailabilities.addAll(validAvailabilities);
        assertEquals(expectedAvailabilities, editedPerson.getAvailabilities());

        // Execute the command and expect a CommandException to be thrown
        CommandException thrown = assertThrows(CommandException.class, () -> addAvailCommand.execute(model));

        // Verify the error message
        assertEquals(AddAvailCommand.MESSAGE_DUPLICATE_AVAIL, thrown.getMessage());
    }


    @Test
    public void createEditedPerson_validInput_success() {
        Person originalPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = AddAvailCommand.createEditedPerson(originalPerson, validAvailabilities);

        Set<Availability> expectedAvailabilities = new HashSet<>(originalPerson.getAvailabilities());
        expectedAvailabilities.addAll(validAvailabilities);

        assertEquals(expectedAvailabilities, editedPerson.getAvailabilities());
    }

    @Test
    public void equalsTest() {
        AddAvailCommand firstAddAvailCommand = new AddAvailCommand(INDEX_FIRST_PERSON, validAvailabilities);
        AddAvailCommand secondAddAvailCommand = new AddAvailCommand(INDEX_SECOND_PERSON, validAvailabilities);
        AddAvailCommand thirdAddAvailCommand = new AddAvailCommand(INDEX_FIRST_PERSON, validAvailabilitiesSecond);
        AddAvailCommand fourthAddAvailCommand = new AddAvailCommand(INDEX_SECOND_PERSON, validAvailabilitiesSecond);


        assertFalse(firstAddAvailCommand.equals(secondAddAvailCommand));
        assertFalse(firstAddAvailCommand.equals(thirdAddAvailCommand));
        assertFalse(firstAddAvailCommand.equals(fourthAddAvailCommand));
        assertTrue(firstAddAvailCommand.equals(firstAddAvailCommand));
    }

    @Test
    public void toStringTest() {
        AddAvailCommand addAvailCommand = new AddAvailCommand(INDEX_FIRST_PERSON, validAvailabilities);
        StringBuilder expectedBuilder = new StringBuilder();
        expectedBuilder.append(AddAvailCommand.class.getCanonicalName());
        expectedBuilder.append("{index=").append(INDEX_FIRST_PERSON).append(", availabilities=");
        expectedBuilder.append(validAvailabilities.stream()
            .map(Object::toString)
            .collect(Collectors.joining(", ", "[", "]")));
        expectedBuilder.append("}");

        String expected = expectedBuilder.toString();

        assertTrue(addAvailCommand.toString().equals(expected));
    }
}
