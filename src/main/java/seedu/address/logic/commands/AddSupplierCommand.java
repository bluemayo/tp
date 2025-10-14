package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DAYS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.model.person.supplier.Supplier;

/**
 * Adds a Supplier to the address book.
 * Command word mirrors the existing pattern ("add role …").
 * Example:
 *   add supplier n/Ah Hock p/98765432 e/ahhock@example.com a/123 Clementi Ave 3 t/preferred
 *               it/Flour,Eggs dy/MON,THU no/halal supplier
 */
public class AddSupplierCommand extends AddCommand {

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

    /**
     * Creates an {@code AddSupplierCommand} to add the specified {@link Supplier}.
     */
    public AddSupplierCommand(Supplier supplier) {
        super(supplier);
    }
}
