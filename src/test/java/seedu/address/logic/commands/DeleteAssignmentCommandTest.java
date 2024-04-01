package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;

/**
 * Contains integration tests (interaction with the Model) and the unit tests for
 * {@code DeleteAssignmentCommand}
 */
public class DeleteAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Assignment assignmentToDelete = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(DeleteAssignmentCommand.MESSAGE_DELETE_ASSIGNMENT_SUCCESS,
                Messages.format(assignmentToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteAssignment(assignmentToDelete);

        assertCommandSuccess(deleteAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(outOfBoundIndex);

        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGES_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }


    @Test
    public void equalsTest() {
        DeleteAssignmentCommand deleteFirstAssignment = new DeleteAssignmentCommand(INDEX_FIRST_ASSIGNMENT);
        DeleteAssignmentCommand deleteSecondAssignment = new DeleteAssignmentCommand(INDEX_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(deleteFirstAssignment.equals(deleteFirstAssignment));

        // same values -> returns true
        DeleteAssignmentCommand deleteFirstAssignmentCopy = new DeleteAssignmentCommand(INDEX_FIRST_ASSIGNMENT);
        assertTrue(deleteFirstAssignment.equals(deleteFirstAssignmentCopy));

        // different types -> returns false
        assertFalse(deleteFirstAssignment.equals(1));

        // null -> returns false
        assertFalse(deleteFirstAssignment.equals(null));

        //different assignment
        assertFalse(deleteFirstAssignment.equals(deleteSecondAssignment));
    }

    @Test
    public void toStringTest() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(targetIndex);
        String expected = DeleteAssignmentCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected ,deleteAssignmentCommand.toString());
    }

}
