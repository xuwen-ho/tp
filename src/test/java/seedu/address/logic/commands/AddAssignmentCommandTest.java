package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DETAILS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AVAILABILITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.AssignmentBuilder.DEFAULT_AVAILABILITY;
import static seedu.address.testutil.AssignmentBuilder.DEFAULT_DETAILS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentDetails;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.PersonBuilder;


/**
 * Test the AddAssignmentCommand
 */
public class AddAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(null, null, null));
    }


    @Test
    public void execute_assignmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAssignmentAdded modelStub = new ModelStubAcceptingAssignmentAdded();
        Assignment assignment = new AssignmentBuilder().build();
        AssignmentDetails details = new AssignmentDetails(DEFAULT_DETAILS);
        Availability availability = new Availability(DEFAULT_AVAILABILITY);
        Index index = new IndexStub();
        CommandResult commandResult = new AddAssignmentCommand(index, details, availability)
                .execute(modelStub);

        assertEquals(String.format(AddAssignmentCommand.MESSAGE_SUCCESS, Messages.format(assignment)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(assignment), modelStub.assignmentsAdded);
    }

    @Test
    public void equalsTest() {
        Assignment assignment = new AssignmentBuilder().build();
        Assignment assignmentCopy = new AssignmentBuilder().build();

        assertTrue(assignment.equals(assignment));
        assertTrue(assignment.equals(assignmentCopy));
        assertFalse(assignment.equals(null));
    }

    @Test
    public void execute_invalidPersonAtIndexFilterList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        AssignmentDetailsStub assignmentDetailsStub = new AssignmentDetailsStub();
        AvailabilityStub availabilityStub = new AvailabilityStub();
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        AddAssignmentCommand addAssignmentCommand = new AddAssignmentCommand(outOfBoundIndex,
                assignmentDetailsStub, availabilityStub);
        assertCommandFailure(addAssignmentCommand, model,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * A default index stub with first index.
     */
    private static class IndexStub extends Index {
        private int zeroBasedIndex;
        private IndexStub() {
            this.zeroBasedIndex = 1;
        }
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
        public void deleteAssignment(Assignment target) {

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
     * A Model stub that always accept the assignment being added.
     */
    private class ModelStubAcceptingAssignmentAdded extends ModelStub {
        final ArrayList<Assignment> assignmentsAdded = new ArrayList<>();

        final ObservableList<Person> personsAdded;

        private ModelStubAcceptingAssignmentAdded() {
            UniquePersonList personList = new UniquePersonList();
            Person person = new PersonBuilder().build();
            personList.add(person);
            personsAdded = personList.asUnmodifiableObservableList();
        }

        @Override
        public void addAssignment(Assignment assignment) {
            requireNonNull(assignment);
            assignmentsAdded.add(assignment);
        }

        @Override
        public boolean hasAssignment(Assignment assignment) {
            requireNonNull(assignment);
            return assignmentsAdded.stream().anyMatch(assignment::equals);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return personsAdded;
        }
    }

    private class AssignmentDetailsStub extends AssignmentDetails {
        private String details;
        private AssignmentDetailsStub() {
            super(VALID_ASSIGNMENT_DETAILS);
        }
    }

    private class AvailabilityStub extends Availability {

        /**
         * Constructs an {@code Address}.
         */
        public AvailabilityStub() {
            super(VALID_AVAILABILITY_AMY);
        }
    }
}
