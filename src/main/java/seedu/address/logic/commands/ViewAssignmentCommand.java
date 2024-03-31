package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Switches the view to see all assignments
 */
public class ViewAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "lista";

    public static final String MESSAGE_SUCCESS = "Here are your list of assignments";

    /**
     * Switches the view to see all assignments
     * @param model {@code Model} which the command should operate on.
     * @return
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.ListPanelView.ASSIGNMENT);
    }
}
