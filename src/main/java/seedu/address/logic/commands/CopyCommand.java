package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copy the emails of all the people in the filtered list\n";

    public static final String MESSAGE_COPY_SUCCESS = "Emails copied to clipboard";

//    private final Index targetIndex;

//    public CopyCommand(Index targetIndex) {
//        this.targetIndex = targetIndex;
//    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
//        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
//        model.deletePerson(personToDelete);

//        model.copyEmailsToClipboard();
        copyEmailsToClipboard(lastShownList);

        return new CommandResult(String.format(MESSAGE_COPY_SUCCESS));
    }

    public void copyEmailsToClipboard(List<Person> filteredPersons) {
        StringBuilder sb = new StringBuilder();

        for (Person person : filteredPersons) {
            String email = person.getEmail().toString();
            sb.append(email);
            sb.append(", ");
        }

        // remove last 2 ", " from the final email
        int strlen = sb.length();
        if (strlen > 0) {
            sb.delete(strlen - 2, strlen - 1);
        }

        System.out.println(sb.toString());
    }

//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof DeleteCommand)) {
//            return false;
//        }
//
//        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
//        return targetIndex.equals(otherDeleteCommand.targetIndex);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .add("targetIndex", targetIndex)
//                .toString();
//    }
}
