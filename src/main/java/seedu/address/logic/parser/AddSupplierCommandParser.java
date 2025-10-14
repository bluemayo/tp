package seedu.address.logic.parser;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.person.supplier.Days;
import seedu.address.model.person.supplier.Items;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.tag.Tag;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new {@link AddSupplierCommand} object.
 */
public class AddSupplierCommandParser implements Parser<AddSupplierCommand> {

    @Override
    public AddSupplierCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_TAG, PREFIX_ITEMS, PREFIX_DAYS, PREFIX_NOTES);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ITEMS, PREFIX_DAYS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddSupplierCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_ITEMS, PREFIX_DAYS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        List<Items> items = ParserUtil.parseItems(argMultimap.getAllValues(PREFIX_ITEMS));
        List<Days> days = ParserUtil.parseDays(argMultimap.getAllValues(PREFIX_DAYS));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Note note = new Note(argMultimap.getValue(PREFIX_NOTES).orElse(""));
        Supplier supplier = new Supplier(name, phone, email, address, tagList, items, days, note);

        return new AddSupplierCommand(supplier);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

