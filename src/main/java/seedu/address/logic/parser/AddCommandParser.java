package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.CUSTOMER_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAYS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHIFTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.STAFF_TYPE;
import static seedu.address.logic.parser.CliSyntax.SUPPLIER_TYPE;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.staff.Shift;
import seedu.address.model.person.staff.Staff;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution. Depending on the command
     * word after "add", it transfers control to the relevant add command parser.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        String preamble = argMultimap.getPreamble();
        if (!isValidType(preamble)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        switch (preamble) {
        case CUSTOMER_TYPE:
            return parseAddCustomerCommand(args);
        case STAFF_TYPE:
            return parseAddStaffCommand(args);
        case SUPPLIER_TYPE:
            return parseAddSupplierCommand(args);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private boolean isValidType(String input) {
        return input.equals(CUSTOMER_TYPE) || input.equals(STAFF_TYPE) || input.equals(SUPPLIER_TYPE);
    }

    private AddCustomerCommand parseAddCustomerCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCustomerCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Customer customer = new Customer(name, phone, email, address, tagList);
        return new AddCustomerCommand(customer);
    }

    private AddStaffCommand parseAddStaffCommand(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_TAG, PREFIX_SHIFTS, PREFIX_NOTES);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_SHIFTS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_SHIFTS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        List<Shift> shifts = ParserUtil.parseShifts(argMultimap.getAllValues(PREFIX_SHIFTS));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Note notes = new Note(argMultimap.getValue(PREFIX_NOTES).get());
        Staff staff = new Staff(name, phone, email, address, tagList, shifts, notes);

        return new AddStaffCommand(staff);
    }

    private AddSupplierCommand parseAddSupplierCommand(String args) throws ParseException {
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
            } catch (IllegalArgumentException ex) {
                throw new ParseException("Invalid day");
            }
        }
    }
}
