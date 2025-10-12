package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAYS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new {@link AddSupplierCommand} object.
 */
public class AddSupplierCommandParser implements Parser<AddSupplierCommand> {

    @Override
    public AddSupplierCommand parse(String args) throws ParseException {
        // Tokenize using your exact prefixes
        ArgumentMultimap m = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_TAG, PREFIX_ITEMS, PREFIX_DAYS, PREFIX_NOTES);

        // Expect preamble == "supplier"
        String preamble = m.getPreamble();
        if (preamble == null || !preamble.trim().equalsIgnoreCase(AddSupplierCommand.ROLE_KEYWORD)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddSupplierCommand.MESSAGE_USAGE));
        }

        // Required: n/, p/, items/, days/
        if (!arePrefixesPresent(m, PREFIX_NAME, PREFIX_PHONE, PREFIX_ITEMS, PREFIX_DAYS)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddSupplierCommand.MESSAGE_USAGE));
        }

        // Parse required basics
        Name name = ParserUtil.parseName(m.getValue(PREFIX_NAME).orElseThrow());
        Phone phone = ParserUtil.parsePhone(m.getValue(PREFIX_PHONE).orElseThrow());

        // Optional tags
        Set<Tag> tags = ParserUtil.parseTags(m.getAllValues(PREFIX_TAG));

        // Items (comma-separated)
        String itemsRaw = m.getValue(PREFIX_ITEMS).orElse("");
        List<String> items = new ArrayList<>();
        for (String s : itemsRaw.split(",")) {
            String t = s.trim();
            if (!t.isEmpty()) {
                items.add(t);
            }
        }
        if (items.isEmpty()) {
            throw new ParseException("Items list cannot be empty. Provide at least one item via items/<list>.");
        }

        // Days (comma-separated short or full names)
        String daysRaw = m.getValue(PREFIX_DAYS).orElse("");
        EnumSet<DayOfWeek> days = EnumSet.noneOf(DayOfWeek.class);
        for (String s : daysRaw.split(",")) {
            String t = s.trim();
            if (!t.isEmpty()) {
                days.add(parseDay(t));
            }
        }
        if (days.isEmpty()) {
            throw new ParseException("Days list cannot be empty. Provide at least one day via days/<list>.");
        }

        // Notes (optional)
        String notes = m.getValue(PREFIX_NOTES).orElse("");

        // Email/Address: OPTIONAL in CLI — silently provide safe internals
        // (so Supplier/Person constructor validation passes; they won't be shown in Supplier#toString)
        String emailRaw = m.getValue(PREFIX_EMAIL).orElse("na@example.com");
        String addressRaw = m.getValue(PREFIX_ADDRESS).orElse("N/A");
        Email email = ParserUtil.parseEmail(emailRaw);
        Address address = ParserUtil.parseAddress(addressRaw);

        Supplier supplier = new Supplier(name, phone, email, address, tags, items, days, notes);
        return new AddSupplierCommand(supplier);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap mm, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(p -> mm.getValue(p).isPresent());
    }

    private DayOfWeek parseDay(String token) throws ParseException {
        String u = token.toUpperCase();
        switch (u) {
        case "MON": return DayOfWeek.MONDAY;
        case "TUE": return DayOfWeek.TUESDAY;
        case "WED": return DayOfWeek.WEDNESDAY;
        case "THU": return DayOfWeek.THURSDAY;
        case "FRI": return DayOfWeek.FRIDAY;
        case "SAT": return DayOfWeek.SATURDAY;
        case "SUN": return DayOfWeek.SUNDAY;
        default:
            try {
                return DayOfWeek.valueOf(u);
            }
            catch (IllegalArgumentException ex) {
                throw new ParseException("Invalid day");
            }
        }
    }
}
