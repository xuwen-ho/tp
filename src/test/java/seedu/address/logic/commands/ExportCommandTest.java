package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;

public class ExportCommandTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");

    @Test
    public void execute_addressBookPresent_exportSuccessful() throws CommandException {
        ModelStubJsonFilePresent modelStubJsonFilePresent = new ModelStubJsonFilePresent();

        CommandResult commandResult = new ExportCommand().execute(modelStubJsonFilePresent);

        assertEquals(ExportCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_addressBookAbsent_throwsCommandException() throws CommandException {
        ModelStubJsonFileAbsent modelStubJsonFileAbsent = new ModelStubJsonFileAbsent();
        ExportCommand exportCommand = new ExportCommand();
        ;
        assertThrows(CommandException.class, ExportCommand.MESSAGE_DIRECTORY_JSON_FILE_ABSENT,
                () -> exportCommand.execute(modelStubJsonFileAbsent));
    }

    @Test
    public void equals() {
        ExportCommand exportCommand = new ExportCommand();

        // same object -> returns true
        assertTrue(exportCommand.equals(exportCommand));

        // different types -> returns false
        assertFalse(exportCommand.equals(1));

        // null -> returns false
        assertFalse(exportCommand.equals(null));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssignment(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAssignment(Assignment toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAssignmentList(Predicate<Assignment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assignment> getFilteredAssignmentList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains an address book.
     */
    private class ModelStubJsonFilePresent extends ModelStub {
        @Override
        public Path getAddressBookFilePath() {
            return TYPICAL_PERSONS_FILE;
        }
    }

    /**
     * A Model stub that do not contain any address book.
     */
    private class ModelStubJsonFileAbsent extends ModelStub {
        @Override
        public Path getAddressBookFilePath() {
            return null;
        }
    }
}
