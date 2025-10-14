package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.person.staff.Shift;
import seedu.address.model.person.staff.Staff;
import seedu.address.model.tag.Tag;

public class AddStaffCommandParserTest {

    private AddStaffCommandParser parser;

    @BeforeEach
    public void setUp() {
        parser = new AddStaffCommandParser();
    }

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        String userInput = " n/Alice p/91234567 e/alice@example.com a/123 Clementi Rd "
                + " shifts/2025-10-15, 2025-10-20 "
                + " t/friendly "
                + " notes/Part-time staff";

        AddStaffCommand command = parser.parse(userInput);

        Staff expectedStaff = new Staff(
                new Name("Alice"),
                new Phone("91234567"),
                new Email("alice@example.com"),
                new Address("123 Clementi Rd"),
                Set.of(new Tag("friendly")),
                List.of(new Shift(LocalDate.of(2025, 10, 15)),
                        new Shift(LocalDate.of(2025, 10, 20))),
                new Note("Part-time staff")
        );

        assertEquals(new AddStaffCommand(expectedStaff), command);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        // Missing phone
        String userInput = " n/Alice e/alice@example.com a/123 Clementi Rd shifts/2025-10-15";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_duplicateSingleValuedPrefix_failure() {
        // Duplicate name
        String userInput = " n/Alice n/Bob p/91234567 e/alice@example.com a/123 Clementi Rd shifts/2025-10-15";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_optionalFieldsMissing_success() throws Exception {
        // No tags, no notes
        String userInput = " n/Alice p/91234567 e/alice@example.com a/123 Clementi Rd shifts/2025-10-15";

        AddStaffCommand command = parser.parse(userInput);

        Staff expectedStaff = new Staff(
                new Name("Alice"),
                new Phone("91234567"),
                new Email("alice@example.com"),
                new Address("123 Clementi Rd"),
                Set.of(), // no tags
                List.of(new Shift(LocalDate.of(2025, 10, 15))),
                new Note("") // empty notes
        );

        assertEquals(new AddStaffCommand(expectedStaff), command);
    }
}
