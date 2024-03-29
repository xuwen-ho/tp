package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSIGNMENT_DETAILS_FOODDONATION;
import static seedu.address.logic.commands.CommandTestUtil.AVAILABILITY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DETAILS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AVAILABILITY_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAssignmentCommand;
import seedu.address.model.assignment.AssignmentDetails;
import seedu.address.model.person.Availability;

public class AddAssignmentParserTest {

    private AddAssignmentParser parser = new AddAssignmentParser();

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddAssignmentCommand.MESSAGE_USAGE);

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        AssignmentDetails details = new AssignmentDetailsStub();
        Availability availability = new AvailabilityStub();
        AddAssignmentCommand expectedCommand = new AddAssignmentCommand(targetIndex, details, availability);
        assertParseSuccess(parser, targetIndex.getOneBased()
                        + ASSIGNMENT_DETAILS_FOODDONATION + AVAILABILITY_DESC_AMY, expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, VALID_ASSIGNMENT_DETAILS, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-1" + VALID_ASSIGNMENT_DETAILS
                + VALID_AVAILABILITY_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_ASSIGNMENT_DETAILS
                + VALID_AVAILABILITY_AMY, MESSAGE_INVALID_FORMAT);
    }


    private class AssignmentDetailsStub extends AssignmentDetails {
        private String details;
        private AssignmentDetailsStub() {
            super(VALID_ASSIGNMENT_DETAILS);
        }
    }

    private class AvailabilityStub extends Availability {

        /**
         * Constructs an {@code Address}.
         */
        public AvailabilityStub() {
            super(VALID_AVAILABILITY_AMY);
        }
    }
}
