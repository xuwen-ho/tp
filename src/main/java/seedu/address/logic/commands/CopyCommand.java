package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copy the emails of all the people in the filtered list\n";

    public static final String MESSAGE_COPY_SUCCESS = "Emails copied to clipboard";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

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

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Create a StringSelection object to hold the text
        StringSelection selection = new StringSelection(sb.toString());

        // Set the contents of the clipboard to the StringSelection
        clipboard.setContents(selection, null);
    }
}
