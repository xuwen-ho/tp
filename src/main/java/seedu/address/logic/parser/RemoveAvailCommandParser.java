package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAIL;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveAvailCommand;
import seedu.address.logic.commands.RemoveAvailCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Availability;

/**
 * Parses input arguments and creates a new RemoveAvailCommand object
 */
public class RemoveAvailCommandParser implements Parser<RemoveAvailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveAvailCommand
     * and returns a RemoveAvailCommand object for execution.
     * @param args The string containing the user input arguments.
     * @return A RemoveAvailCommand object representing the user's command.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public RemoveAvailCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_AVAIL);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveAvailCommand.MESSAGE_USAGE), pe);
        }

        List<String> availabilityValues = argMultimap.getAllValues(PREFIX_AVAIL);
        if (availabilityValues.isEmpty()) {
            throw new ParseException(RemoveAvailCommand.MESSAGE_NOT_REMOVE_AVAIL);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        parseAvailabilitiesForEdit(availabilityValues)
            .ifPresent(editPersonDescriptor::setAvailabilities);

        return new RemoveAvailCommand(index, editPersonDescriptor);
    }

    private Optional<Set<Availability>> parseAvailabilitiesForEdit(List<String> availabilities)
            throws ParseException {
        Set<Availability> availabilitySet = new HashSet<>();
        for (String availability : availabilities) {
            if (availability.isBlank()) {
                throw new ParseException("Availability cannot be empty.");
            }
            availabilitySet.add(ParserUtil.parseAvailability(availability));
        }
        return Optional.of(availabilitySet);
    }
}
