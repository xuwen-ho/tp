package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Copy all the emails of the people in the filtered list
 * Keyword matching is case insensitive.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copy the emails of all the people in the filtered list\n";

    public static final String MESSAGE_COPY_SUCCESS = "Emails copied to clipboard";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.isEmpty()) {
            throw new CommandException("There is no person currently displayed");
        }

        String emailString = extractEmails(lastShownList);
        copyToClipboard(emailString);

        return new CommandResult(String.format(MESSAGE_COPY_SUCCESS));
    }

    /**
     * Extracts email addresses from a list of Person objects and returns them as a comma-separated string.
     *
     * @param filteredPersons A list of Person objects.
     * @return A comma-separated string containing email addresses of the provided Person objects.
     */
    public String extractEmails(List<Person> filteredPersons) {
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

        return sb.toString();
    }

    /**
     * Copies the provided text to the system clipboard.
     *
     * @param desiredText The text to be copied to the clipboard.
     */
    public void copyToClipboard(String desiredText) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Create a StringSelection object to hold the text
        StringSelection selection = new StringSelection(desiredText);

        // Set the contents of the clipboard to the StringSelection
        clipboard.setContents(selection, null);
    }
}
