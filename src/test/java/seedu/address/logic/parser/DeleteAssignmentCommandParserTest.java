package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAssignmentCommand;

/**
 * Similar to DeleteCommandParserTest, we do not test path variations outside
 * the DeleteAssignmentCommand code.
 */
public class DeleteAssignmentCommandParserTest {

    private DeleteAssignmentCommandParser parser = new DeleteAssignmentCommandParser();


    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteAssignmentCommand(INDEX_FIRST_ASSIGNMENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,"a",String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAssignmentCommand.MESSAGE_USAGE));
    }
}
