package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

/**
 * Deletes all availabilities before a specified date from all persons in the address book.
 */
public class RefreshCommand extends Command {

    public static final String COMMAND_WORD = "refresh";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes all availabilities before the specified date from all persons in the address book.\n"
        + "Parameters: DATE (in the format dd/MM/yyyy)\n"
        + "Example: " + COMMAND_WORD + " a/01/01/2022";

    public static final String MESSAGE_REFRESH_SUCCESS = "All availabilities before %1$s have been deleted.";

    public static final String VALIDATION_REGEX = "dd/MM/yyyy";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(VALIDATION_REGEX);

    private final Availability dateToDelete;

    /**
     * Creates a RefreshAvailCommand to delete availabilities before the specified date.
     */
    public RefreshCommand() {
        LocalDate currentDate = LocalDate.now();
        this.dateToDelete = new Availability(currentDate.format(formatter));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Filter and update availabilities for each person
        Set<Person> personsToUpdate = updatePersonAvailabilities(model, dateToDelete);

        // Update the model with updated persons
        updateModelWithPersons(model, personsToUpdate);

        return new CommandResult(String.format(MESSAGE_REFRESH_SUCCESS, dateToDelete));
    }

    private Set<Person> updatePersonAvailabilities(Model model, Availability dateToDelete) {
        return model.getAddressBook().getPersonList().stream()
            .map(person -> updatePersonAvailability(person, dateToDelete))
            .collect(Collectors.toSet());
    }

    private Person updatePersonAvailability(Person person, Availability dateToDelete) {
        Set<Availability> updatedAvailabilities = person.getAvailabilities().stream()
            .filter(availability -> !availability.getDate().isBefore(dateToDelete.getDate()))
            .collect(Collectors.toSet());
        return new Person(person.getName(), person.getPhone(), person.getEmail(),
            updatedAvailabilities, person.getTags());
    }

    private void updateModelWithPersons(Model model, Set<Person> personsToUpdate) {
        personsToUpdate.forEach(editedPerson ->
            model.getFilteredPersonList().stream()
                .filter(person -> person.isSamePerson(editedPerson))
                .findFirst()
                .ifPresent(personToEdit -> model.setPerson(personToEdit, editedPerson))
        );
    }
    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof RefreshCommand // instanceof handles nulls
            && dateToDelete.equals(((RefreshCommand) other).dateToDelete)); // state check
    }
}
