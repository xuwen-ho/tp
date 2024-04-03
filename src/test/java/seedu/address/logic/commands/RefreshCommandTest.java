package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RefreshCommand.
 */
public class RefreshCommandTest {
    private Model expectedModel;
    private RefreshCommand refreshCommand;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_refreshBeforeDateWithAvailabilities_success() {
        // Choose a date before which there are availabilities
        Availability dateToDelete = new Availability(LocalDate.now().format(formatter));

        RefreshCommand refreshCommand = new RefreshCommand();

        String expectedMessage = String.format(RefreshCommand.MESSAGE_REFRESH_SUCCESS, dateToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(refreshCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        assertEquals(refreshCommand, new RefreshCommand());
    }
}
