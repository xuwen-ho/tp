package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

public class RemoveAvailCommandTest {

    private final Set<Availability> validAvailabilities = new HashSet<>() {{
                add(new Availability("14/02/2024"));
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
        assertThrows(NullPointerException.class, () -> new RemoveAvailCommand(null, validAvailabilities));
    }

    @Test
    public void constructor_nullAvailabilities_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveAvailCommand(Index.fromZeroBased(1),
            null));
    }

    @Test
    public void constructor_nullIndexAvailabilities_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveAvailCommand(null, null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        // Create an index that is out of bounds
        Index invalidIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        // Create an RemoveAvailCommand with the invalid index and a valid availability
        RemoveAvailCommand removeAvailCommand = new RemoveAvailCommand(invalidIndex, validAvailabilities);

        // Execute the command and expect a CommandException to be thrown
        CommandException thrown = assertThrows(CommandException.class, () -> removeAvailCommand.execute(model));

        // Verify the error message
        assertEquals(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, thrown.getMessage());
    }

    @Test
    public void execute_validInput_success() throws CommandException {
        // Create a RemoveAvailCommand with valid index and availabilities
        RemoveAvailCommand removeAvailCommand = new RemoveAvailCommand(INDEX_FIRST_PERSON, validAvailabilities);

        // Get the person to edit from the model
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // Ensure that the person has the availabilities to be removed
        Set<Availability> existingAvailabilities = new HashSet<>(personToEdit.getAvailabilities());
        assertTrue(existingAvailabilities.containsAll(validAvailabilities));

        // Execute the command
        CommandResult commandResult = removeAvailCommand.execute(model);

        // Get the edited person after execution
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // Verify that the availabilities have been removed from the person
        Set<Availability> expectedAvailabilities = new HashSet<>(personToEdit.getAvailabilities());
        expectedAvailabilities.removeAll(validAvailabilities);
        assertEquals(expectedAvailabilities, editedPerson.getAvailabilities());

        // Verify that the model is updated with the edited person
        assertEquals(editedPerson, model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));

        // Verify that the filtered person list is updated
        assertTrue(model.getFilteredPersonList().contains(editedPerson));

        // Verify that the correct CommandResult is returned
        String expectedFeedback = String.format(RemoveAvailCommand.MESSAGE_REMOVE_AVAILABILITY_SUCCESS,
            Messages.formatAvailability(editedPerson));
        assertEquals(expectedFeedback, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_nonExistentAvailability_throwsCommandException() {
        // Create a RemoveAvailCommand with an index and availabilities
        RemoveAvailCommand removeAvailCommand = new RemoveAvailCommand(INDEX_FIRST_PERSON,
            validAvailabilitiesSecond);

        // Get the person to edit from the model
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // Ensure that the person does not have the availabilities to be removed
        Set<Availability> existingAvailabilities = new HashSet<>(personToEdit.getAvailabilities());
        assertFalse(existingAvailabilities.containsAll(validAvailabilitiesSecond));

        // Execute the command and expect a CommandException to be thrown
        CommandException thrown = assertThrows(CommandException.class, () -> removeAvailCommand.execute(model));

        // Verify that the correct error message is thrown
        assertEquals(RemoveAvailCommand.MESSAGE_AVAIL_NOT_FOUND, thrown.getMessage());
    }


    @Test
    public void createEditedPerson_validInput_success() {
        Person originalPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = RemoveAvailCommand.createEditedPerson(originalPerson, validAvailabilities);

        Set<Availability> expectedAvailabilities = new HashSet<>(originalPerson.getAvailabilities());
        expectedAvailabilities.removeAll(validAvailabilities);

        assertEquals(expectedAvailabilities, editedPerson.getAvailabilities());
    }

    @Test
    public void equalsTest() {
        RemoveAvailCommand firstRemoveAvailCommand = new RemoveAvailCommand(INDEX_FIRST_PERSON, validAvailabilities);
        RemoveAvailCommand secondRemoveAvailCommand = new RemoveAvailCommand(INDEX_SECOND_PERSON, validAvailabilities);
        RemoveAvailCommand thirdRemoveAvailCommand = new RemoveAvailCommand(INDEX_FIRST_PERSON,
            validAvailabilitiesSecond);
        RemoveAvailCommand fourthRemoveAvailCommand = new RemoveAvailCommand(INDEX_SECOND_PERSON,
            validAvailabilitiesSecond);

        assertTrue(firstRemoveAvailCommand.equals(firstRemoveAvailCommand));
        assertFalse(firstRemoveAvailCommand.equals(secondRemoveAvailCommand));
        assertFalse(firstRemoveAvailCommand.equals(thirdRemoveAvailCommand));
        assertFalse(firstRemoveAvailCommand.equals(fourthRemoveAvailCommand));
    }

    @Test
    public void equals_differentClass_returnFalse() {
        RemoveAvailCommand removeAvailCommand = new RemoveAvailCommand(INDEX_FIRST_PERSON, validAvailabilities);

        // Create an instance of a different class
        Object otherObject = new Object();

        // Ensure that equals returns false when comparing with an instance of a different class
        assertFalse(removeAvailCommand.equals(otherObject));
    }

    @Test
    public void toStringTest() {
        RemoveAvailCommand removeAvailCommand = new RemoveAvailCommand(INDEX_FIRST_PERSON, validAvailabilities);
        StringBuilder expectedBuilder = new StringBuilder();
        expectedBuilder.append(RemoveAvailCommand.class.getCanonicalName());
        expectedBuilder.append("{index=").append(INDEX_FIRST_PERSON).append(", availabilities=");
        expectedBuilder.append(validAvailabilities.stream().map(Object::toString).collect(Collectors.joining(", ",
            "[", "]")));
        expectedBuilder.append("}");

        String expected = expectedBuilder.toString();

        assertTrue(removeAvailCommand.toString().equals(expected));
    }
}
