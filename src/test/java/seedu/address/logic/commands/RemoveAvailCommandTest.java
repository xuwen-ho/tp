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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

public class RemoveAvailCommandTest {

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
    public void execute_removeNonExistentAvailability_throwsCommandException() {
        RemoveAvailCommand removeAvailCommand = new RemoveAvailCommand(INDEX_FIRST_PERSON, validAvailabilities);

        // Ensure that the person at INDEX_FIRST_PERSON does not have validAvailabilities initially
        ModelManager modelWithNoAvailabilities = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person personToEdit = modelWithNoAvailabilities.getFilteredPersonList()
            .get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Availability> existingAvailabilities = new HashSet<>(personToEdit.getAvailabilities());

        assertTrue(existingAvailabilities.stream().noneMatch(validAvailabilities::contains));

        // Ensure that executing the command throws CommandException with appropriate message
        CommandException thrown = assertThrows(CommandException.class, (
                ) -> removeAvailCommand.execute(modelWithNoAvailabilities));

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
