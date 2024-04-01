package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RefreshCommand;
import seedu.address.model.person.Availability;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the RefreshCommand code. For example, inputs "a" and "a abc" take the
 * same path through the RefreshCommandParser, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class RefreshCommandParserTest {

    private final RefreshCommandParser parser = new RefreshCommandParser();
    private final Availability firstDate = new Availability("01/01/2022");
    private final Availability secondDate = new Availability("02/01/2022");

    @Test
    public void parse_validArgs_returnsRefreshCommand() {
        assertParseSuccess(parser, " a/01/01/2022", new RefreshCommand(firstDate));
        assertParseSuccess(parser, " a/02/01/2022", new RefreshCommand(secondDate));
    }

    @Test
    public void parse_missingPrefix_throwsParseException() {
        // missing prefix
        assertParseFailure(parser, "01/01/2022", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            RefreshCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid date format
        assertParseFailure(parser, " a/2022-01-01", Availability.MESSAGE_CONSTRAINTS);
    }
}
