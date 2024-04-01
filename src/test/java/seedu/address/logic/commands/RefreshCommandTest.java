package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Availability;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RefreshCommand}.
 */
public class RefreshCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_refreshBeforeDateWithAvailabilities_success() {
        // Choose a date before which there are availabilities
        Availability dateToDelete = new Availability("01/01/2022");

        RefreshCommand refreshCommand = new RefreshCommand(dateToDelete);

        String expectedMessage = String.format(RefreshCommand.MESSAGE_REFRESH_SUCCESS, dateToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(refreshCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Availability firstDate = new Availability("01/01/2022");
        Availability secondDate = new Availability("02/01/2022");

        RefreshCommand refreshFirstCommand = new RefreshCommand(firstDate);
        RefreshCommand refreshSecondCommand = new RefreshCommand(secondDate);

        // same object -> returns true
        assertTrue(refreshFirstCommand.equals(refreshFirstCommand));

        // same values -> returns true
        RefreshCommand refreshFirstCommandCopy = new RefreshCommand(firstDate);
        assertTrue(refreshFirstCommand.equals(refreshFirstCommandCopy));

        // different types -> returns false
        assertFalse(refreshFirstCommand.equals(1));

        // null -> returns false
        assertFalse(refreshFirstCommand.equals(null));

        // different dates -> returns false
        assertFalse(refreshFirstCommand.equals(refreshSecondCommand));
    }
}
