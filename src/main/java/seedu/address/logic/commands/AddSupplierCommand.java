package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAYS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.supplier.Supplier;

/**
 * Adds a Supplier to the address book.
 * Command word mirrors the existing pattern ("add <role> …").
 * Example:
 *   add supplier n/Ah Hock p/98765432 e:ahhock@example.com a/123 Clementi Ave 3 t/preferred
 *               it/Flour,Eggs dy/MON,THU no/halal supplier
 */
public class AddSupplierCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String ROLE_KEYWORD = "supplier";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + ROLE_KEYWORD + ": Adds a supplier. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_ITEMS + "item1[,item2,...] "
            + PREFIX_DAYS + "MON[,TUE,...] "
            + "[" + PREFIX_NOTES + "NOTES]\n"
            + "Example: " + COMMAND_WORD + " " + ROLE_KEYWORD + " "
            + PREFIX_NAME + "Ah Hock "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_ITEMS + "Flour,Eggs "
            + PREFIX_DAYS + "MON,THU "
            + PREFIX_NOTES + "halal supplier";

    public static final String MESSAGE_SUCCESS = "New supplier added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This supplier already exists in the address book";

    private final Supplier toAdd;

    public AddSupplierCommand(Supplier supplier) {
        requireNonNull(supplier);
        this.toAdd = supplier;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddSupplierCommand
                && toAdd.equals(((AddSupplierCommand) other).toAdd));
    }
}
