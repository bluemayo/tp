package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHIFTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.staff.Staff;

/**
 * Adds a Staff to the address book.
 * Command word mirrors the existing pattern ("add role …").
 * Example:
 *   add staff n/Ah Hock p/98765432 e:ahhock@example.com a/123 Clementi Ave 3 t/preferred
 *   shifts/2025-12-03, 2025-12-04, 2025-12-05 no/can only do weekdays
 *
 */
public class AddStaffCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String ROLE_KEYWORD = "staff";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + ROLE_KEYWORD + ": Adds a staff. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]... "
            + PREFIX_SHIFTS + "2025-12-04[,2025-12-05,...] "
            + "[" + PREFIX_NOTES + "NOTES]\n"
            + "Example: " + COMMAND_WORD + " " + ROLE_KEYWORD + " "
            + PREFIX_NAME + "Ah Hock "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "ahhock@example.com "
            + PREFIX_ADDRESS + "123 Clementi Ave 3 "
            + PREFIX_TAG + "preferred "
            + PREFIX_SHIFTS + "2025-12-04 "
            + PREFIX_NOTES + "can only do weekdays";

    public static final String MESSAGE_SUCCESS = "New staff added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This staff already exists in the address book";

    private final Staff toAdd;

    /**
     * Initialises toAdd to the parameter staff given.
     */
    public AddStaffCommand(Staff staff) {
        requireNonNull(staff);
        this.toAdd = staff;
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
                || (other instanceof AddStaffCommand
                && toAdd.equals(((AddStaffCommand) other).toAdd));
    }
}
