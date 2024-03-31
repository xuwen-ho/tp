package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAIL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAvailCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Availability;

/**
 * Parses the given {@code String} of arguments in the context of the AddAvailCommand
 * and returns an AddAvailCommand object for execution.
 * @return An AddAvailCommand object representing the user's command.
 * @throws ParseException if the user input does not conform to the expected format.
 */
public class AddAvailCommandParser implements Parser<AddAvailCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddAvailCommand
     * and returns an AddAvailCommand object for execution.
     *
     * @param args The input arguments provided by the user.
     * @return An AddAvailCommand object representing the user's command.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddAvailCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_AVAIL);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAvailCommand.MESSAGE_USAGE), pe);
        }

        List<String> availabilityValues = argMultimap.getAllValues(PREFIX_AVAIL);
        if (availabilityValues.isEmpty()) {
            throw new ParseException(AddAvailCommand.MESSAGE_NOT_ADD_AVAIL);
        }

        Set<Availability> availabilities = new HashSet<>();
        for (String availabilityValue : availabilityValues) {
            // Parse each availability string
            Availability availability;
            availability = ParserUtil.parseAvailability(availabilityValue);
            availabilities.add(availability);
        }

        return new AddAvailCommand(index, availabilities);
    }
}






