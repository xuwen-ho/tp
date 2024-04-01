package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

/**
 * Deletes an assignment identified using it's displayed index from the address book.
 */
public class DeleteAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "removeassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the assignment identified by the index number used in the displayed"
            + " assignment list.\n"
            + "Parameters: INDEX (must be a postive integer)\n"
            + "Example: " + COMMAND_WORD + "1";

    public static final String MESSAGE_DELETE_ASSIGNMENT_SUCCESS = "Assignment removed : %1$s";

    private final Index targetIndex;

    public DeleteAssignmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Assignment> lastShowList = model.getFilteredAssignmentList();

        if (targetIndex.getZeroBased() >= lastShowList.size()) {
            throw new CommandException(Messages.MESSAGES_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment assignmentToDelete = lastShowList.get(targetIndex.getZeroBased());
        model.deleteAssignment(assignmentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ASSIGNMENT_SUCCESS,
                Messages.format(assignmentToDelete)));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteAssignmentCommand)) {
            return false;
        }

        DeleteAssignmentCommand otherDeleteAssignmentCommand = (DeleteAssignmentCommand) other;
        return targetIndex.equals(otherDeleteAssignmentCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
