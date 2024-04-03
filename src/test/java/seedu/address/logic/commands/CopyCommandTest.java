package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_EMPTY_LIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsEmails;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


public class CopyCommandTest {

    private Model model;
    private Model expectedModel;
    private Model newModel;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        newModel = new ModelManager(new AddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        CopyCommand copyCommand = new CopyCommand();

        // same object -> returns true
        assertTrue(copyCommand.equals(copyCommand));

        // different types -> returns false
        assertFalse(copyCommand.equals(1));

        // null -> returns false
        assertFalse(copyCommand.equals(null));
    }
    @Test
    public void execute_emptyList_noEmailsCopied() {
        assertCommandFailure(new CopyCommand(), newModel, MESSAGE_EMPTY_LIST);
    }
    @Test
    public void executeEmailsCopied() {
        assertCommandSuccess(new CopyCommand(), model, CopyCommand.MESSAGE_COPY_SUCCESS, model);
    }

    @Test
    public void extractEmailsMethod() {
        List<Person> lastShownList = getTypicalPersons();
        CopyCommand copyCommand = new CopyCommand();
        String emails = copyCommand.extractEmails(lastShownList);
        String expectedEmails = getTypicalPersonsEmails();

        assertTrue(emails.equals(expectedEmails));
    }

}
