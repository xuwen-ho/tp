package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class ViewAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "lista";

    public static final String MESSAGE_SUCCESS = "Here are your list of assignments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
//        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.ListPanelView.ASSIGNMENT);
    }
}
