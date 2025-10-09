package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.supplier.Supplier; // note: your project places Supplier here
import seedu.address.model.tag.Tag;

public class AddSupplierCommandParserTest {

    private final AddSupplierCommandParser parser = new AddSupplierCommandParser();

    @Test
    public void parse_success_minimal_requiredOnly() throws Exception {
        // minimal valid: required fields present; email/address omitted on purpose
        String input = " supplier n/Ah Hock p/98765432 items/Flour,Eggs days/MON,THU";

        AddSupplierCommand cmd = parser.parse(input);

        Supplier expected = new Supplier(
                new Name("Ah Hock"),
                new Phone("98765432"),
                // Parser supplies internal defaults when omitted:
                new Email("na@example.com"),
                new Address("N/A"),
                Set.<Tag>of(),
                List.of("Flour", "Eggs"),
                EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.THURSDAY),
                "" // no notes
        );

        assertEquals(new AddSupplierCommand(expected), cmd);
    }

    @Test
    public void parse_success_withOptionalNotesAndTags() throws Exception {
        String input = " supplier n/Ah Hock p/98765432 items/Flour,Eggs days/MON,THU "
                + "notes/halal supplier tag/preferred tag/bulk";

        AddSupplierCommand cmd = parser.parse(input);

        Supplier expected = new Supplier(
                new Name("Ah Hock"),
                new Phone("98765432"),
                new Email("na@example.com"),
                new Address("N/A"),
                // tags are parsed inside the command; equality should still hold via Supplier equality
                Set.<Tag>of(new Tag("preferred"), new Tag("bulk")),
                List.of("Flour", "Eggs"),
                EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.THURSDAY),
                "halal supplier"
        );

        assertEquals(new AddSupplierCommand(expected), cmd);
    }

    @Test
    public void parse_failure_missing_required() {
        // missing items/days
        String input = " supplier n/Ah Hock p/98765432";
        assertThrows(ParseException.class, () -> parser.parse(input));
    }

    @Test
    public void parse_failure_bad_day() {
        String input = " supplier n/Ah Hock p/98765432 items/Flour days/MOON";
        assertThrows(ParseException.class, () -> parser.parse(input));
    }
}
