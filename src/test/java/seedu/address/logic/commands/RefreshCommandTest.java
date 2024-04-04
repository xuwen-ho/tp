package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



/**
 * Contains integration tests (interaction with the Model) and unit tests for RefreshCommand.
 */
public class RefreshCommandTest {

    private static final String VALIDATION_REGEX = "dd/MM/yyyy";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(VALIDATION_REGEX);

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        String expectedMessage = String.format(RefreshCommand.MESSAGE_REFRESH_SUCCESS,
            LocalDate.now().format(formatter));
        assertCommandSuccess(new RefreshCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        String expectMessage = String.format(RefreshCommand.MESSAGE_REFRESH_SUCCESS,
            LocalDate.now().format(formatter));
        CommandResult expectedMessage = new CommandResult(expectMessage);
        RefreshCommand command = new RefreshCommand();
        CommandResult result = command.execute(model);
        assertEquals(expectedMessage, result);
    }
    @Test
    public void equals() {
        RefreshCommand refreshCommand = new RefreshCommand();

        // same object -> returns true
        assertTrue(refreshCommand.equals(refreshCommand));

        // same values -> returns true
        assertTrue(refreshCommand.equals(new RefreshCommand()));

        // null -> returns false
        assertFalse(refreshCommand.equals(null));

        // different types -> returns false
        assertFalse(refreshCommand.equals(new ClearCommand()));
    }
}
