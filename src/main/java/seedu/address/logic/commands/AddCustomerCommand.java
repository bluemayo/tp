package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.model.person.customer.Customer;

/**
 * Adds a Supplier to the address book.
 * Format: add customer n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]...
 * Example:
 *   add customer n/John Ng p/98765432 e/johnng@example.com a/123 Clementi Ave 3 t/regular
 */
public class AddCustomerCommand extends AddCommand {

    public static final String COMMAND_WORD = AddCommand.COMMAND_WORD + " customer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a customer to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    /**
     * Creates an AddCustomerCommand to add the specified {@code Customer}
     */
    public AddCustomerCommand(Customer customer) {
        super(customer);
    }
}
