package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.AVAILABILITY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AVAILABILITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AVAILABILITY_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAvailCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Availability;

public class AddAvailCommandParserTest {
    private final AddAvailCommandParser parser = new AddAvailCommandParser();
    private final Set<Availability> validAvailabilities = new HashSet<>() {{
                add(new Availability("01/01/2024"));
        }
    };

    @Test
    public void parse_invalidArgs_throwsParserException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse("1"));
        assertThrows(ParseException.class, () -> parser.parse("" + VALID_AVAILABILITY_AMY));
        assertThrows(ParseException.class, () -> parser.parse("1" + INVALID_AVAILABILITY_DESC));
    }

    @Test
    public void parse_validArgs_success() throws ParseException {
        String userInput;
        AddAvailCommand expected;
        AddAvailCommand command;

        userInput = INDEX_FIRST_PERSON.getOneBased() + AVAILABILITY_DESC_AMY;
        expected = new AddAvailCommand(INDEX_FIRST_PERSON, validAvailabilities);
        command = parser.parse(userInput);
        assertEquals(expected, command);
    }

}
