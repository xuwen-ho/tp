package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RefreshCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Availability;

import java.util.stream.Stream;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAIL;

/**
 * Parses input arguments and creates a new RefreshAvailCommand object
 */
public class RefreshCommandParser implements Parser<RefreshCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RefreshAvailCommand
     * and returns a RefreshAvailCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RefreshCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_AVAIL);

        if (!arePrefixesPresent(argMultimap, PREFIX_AVAIL) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RefreshCommand.MESSAGE_USAGE));
        }

        Availability dateToDelete = ParserUtil.parseAvailability(argMultimap.getValue(PREFIX_AVAIL).get());
        return new RefreshCommand(dateToDelete);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
