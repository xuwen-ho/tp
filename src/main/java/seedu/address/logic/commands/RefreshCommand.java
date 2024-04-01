package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Availability;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Deletes all availabilities before a specified date from all persons in the address book.
 */
public class RefreshCommand extends Command {

    public static final String COMMAND_WORD = "refresh";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes all availabilities before the specified date from all persons in the address book.\n"
        + "Parameters: DATE (in the format dd/MM/yyyy)\n"
        + "Example: " + COMMAND_WORD + " a/01/01/2022";

    public static final String MESSAGE_REFRESH_AVAILABILITY_SUCCESS = "All availabilities before %1$s have been deleted.";

    private final Availability dateToDelete;

    /**
     * Creates a RefreshAvailCommand to delete availabilities before the specified date.
     */
    public RefreshCommand(Availability dateToDelete) {
        requireNonNull(dateToDelete);
        this.dateToDelete = dateToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Set<Availability> availabilitiesToDelete = model.getAddressBook().getPersonList().stream()
            .flatMap(person -> person.getAvailabilities().stream())
            .filter(availability -> availability.getDate().isBefore(dateToDelete.getDate()))
            .collect(Collectors.toSet());

        if (availabilitiesToDelete.isEmpty()) {
            throw new CommandException("No availabilities to delete before the specified date.");
        }

        model.deleteAvailabilities(availabilitiesToDelete);
        return new CommandResult(String.format(MESSAGE_REFRESH_AVAILABILITY_SUCCESS, dateToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof RefreshCommand // instanceof handles nulls
            && dateToDelete.equals(((RefreshCommand) other).dateToDelete)); // state check
    }
}
