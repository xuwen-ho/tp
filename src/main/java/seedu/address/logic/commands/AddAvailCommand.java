package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAIL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds availabilities of an existing person in the address book.
 */
public class AddAvailCommand extends Command {

    public static final String COMMAND_WORD = "addavail";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the availability of the person identified "
        + "by the index number.\n "
        + "Input availability will be added to the existing availabilities.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_AVAIL + "AVAILABILITY] \n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_AVAIL + "26/04/2024";

    public static final String MESSAGE_ADD_AVAILABILITY_SUCCESS = "Availability Added: %1$s";
    public static final String MESSAGE_NOT_ADD_AVAIL = "At least one availability must be provided.";
    public static final String MESSAGE_DUPLICATE_AVAIL = "Duplicate availability entered for this person.";

    private final Index index;
    private final Set<Availability> availabilities;

    /**
     * @param index of the person in the filtered person list to edit
     * @param availabilities date to be added to person
     */
    public AddAvailCommand(Index index, Set<Availability> availabilities) {
        requireNonNull(index);
        requireNonNull(availabilities);

        this.index = index;
        this.availabilities = availabilities;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        // Combine existing and new availabilities
        Set<Availability> existingAvailabilities = new HashSet<>(personToEdit.getAvailabilities());

        for (Availability newAvailability : availabilities) {
            if (existingAvailabilities.contains(newAvailability)) {
                throw new CommandException(MESSAGE_DUPLICATE_AVAIL);
            }
            existingAvailabilities.add(newAvailability);
        }

        Person editedPerson = createEditedPerson(personToEdit, existingAvailabilities);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_AVAILABILITY_SUCCESS,
            Messages.formatAvailability(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Person createEditedPerson(Person personToEdit, Set<Availability> availability) {
        assert personToEdit != null;

        // Retrieve existing details from the personToEdit
        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Set<Availability> existingAvailabilities = new HashSet<>(personToEdit.getAvailabilities());
        Set<Tag> updatedTags = personToEdit.getTags();

        // Add the new availability to the existing set
        existingAvailabilities.addAll(availability);

        return new Person(updatedName, updatedPhone, updatedEmail, existingAvailabilities, updatedTags);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAvailCommand)) {
            return false;
        }

        AddAvailCommand otherAddAvailCommand = (AddAvailCommand) other;
        return index.equals(otherAddAvailCommand.index)
            && availabilities.equals(otherAddAvailCommand.availabilities);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("index", index)
            .add("availabilities", availabilities)
            .toString();
    }
}

