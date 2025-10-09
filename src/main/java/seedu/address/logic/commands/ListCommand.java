package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static enum Category {
        ALL,
        STAFF,
        CUSTOMER,
        SUPPLIER
    }

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List <all | staff | customer | supplier> categories";
    public static final String MESSAGE_SUCCESS = "Listed all persons";
    private static final String MESSAGE_SUCCESS_FORMAT = "Listed %s";

    private Category category;

    public ListCommand(Category category) {
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model) {
        // TODO: Implement logic
        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, this.category.toString().toLowerCase()));
    }
}
