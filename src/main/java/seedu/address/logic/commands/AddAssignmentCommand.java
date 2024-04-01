package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentDetails;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

/**
 * Adds an assignment to the application
 */
public class AddAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "assign";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assignment to the address book. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DETAILS + "DETAILS "
            + PREFIX_AVAIL + "DATE OF ASSIGNMENT "
            + "Example: " + COMMAND_WORD + " "
            + "1 " + PREFIX_DETAILS + "Food Distribution "
            + PREFIX_AVAIL + "01/01/2024";

    public static final String MESSAGE_SUCCESS = "Assignment added successfully: %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This volunteer already have an existing assignment"
            + " on this day";
    public static final String MESSAGE_VOLUNTEER_NOT_AVAILABLE = "This volunteer is not available on this date";


    private final Index index;
    private final AssignmentDetails details;
    private final Availability availability;

    /**
     * Creates an AddAssignment command to add the specified Assignment with these parameters
     * @param index
     * @param details
     * @param availability
     */
    public AddAssignmentCommand(Index index, AssignmentDetails details, Availability availability) {
        requireNonNull(index);
        requireNonNull(details);
        requireNonNull(availability);
        this.index = index;
        this.details = details;
        this.availability = availability;
    }

    /**
     * Creates the assignment and adds it to the application
     * @param model {@code Model} which the command should operate on.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAssign = lastShownList.get(index.getZeroBased());

        if (!personToAssign.isAvailable(this.availability)) {
            throw new CommandException(MESSAGE_VOLUNTEER_NOT_AVAILABLE);
        }

        Assignment toAdd = new Assignment(personToAssign, details, availability);

        if (model.hasAssignment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.addAssignment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAssignmentCommand)) {
            return false;
        }

        AddAssignmentCommand otherAddAssignmentCommand = (AddAssignmentCommand) other;
        return index.equals(otherAddAssignmentCommand.index)
                && availability.equals(otherAddAssignmentCommand.availability)
                && details.equals(otherAddAssignmentCommand.details);
    }
}
