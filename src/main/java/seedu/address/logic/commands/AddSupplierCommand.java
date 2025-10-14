package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddStaffCommand.ROLE_KEYWORD;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.supplier.Supplier;

/**
 * Adds a Supplier to the address book.
 * Command word mirrors the existing pattern ("add role …").
 * Example:
 *   add supplier n/Ah Hock p/98765432 e/ahhock@example.com a/123 Clementi Ave 3 t/preferred
 *               it/Flour,Eggs dy/MON,THU no/halal supplier
 */
public class AddSupplierCommand extends AddCommand {

    public static final String COMMAND_WORD = AddCommand.COMMAND_WORD + " supplier";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a supplier to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_ITEMS + "item1[,item2,...] "
            + PREFIX_DAYS + "MON[,TUE,...] "
            + "[" + PREFIX_NOTES + "NOTES]\n"
            + "Example: " + COMMAND_WORD + " " + ROLE_KEYWORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_ITEMS + "Flour,Eggs "
            + PREFIX_DAYS + "MON,THU "
            + PREFIX_NOTES + "Halal supplier "
            + PREFIX_TAG + "Coffee bean supplier";

    /**
     * Creates an {@code AddSupplierCommand} to add the specified {@link Supplier}.
     *
     * @param supplier the supplier to be added. Must not be {@code null}.
     * @throws NullPointerException if {@code supplier} is null
     */
    public AddSupplierCommand(Supplier supplier) { super(supplier); }
}
