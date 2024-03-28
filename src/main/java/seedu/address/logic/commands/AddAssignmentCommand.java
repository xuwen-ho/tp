package seedu.address.logic.commands;

import java.util.List;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentDetails;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;

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


    private final Index index;
    private final AssignmentDetails details;
    private final Availability availability;

    //index
    public AddAssignmentCommand(Index index, AssignmentDetails details, Availability availability) {
        requireNonNull(index);
        requireNonNull(details);
        requireNonNull(availability);
        this.index = index;
        this.details = details;
        this.availability = availability;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAssign = lastShownList.get(index.getZeroBased());

        Assignment toAdd = new Assignment(personToAssign,details,availability);
        model.addAssignment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }
}
