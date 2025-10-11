package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.Map;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {
    public enum Category {
        ALL("all", Model.PREDICATE_SHOW_ALL_PERSONS),
        STAFF("staff", Model.PREDICATE_SHOW_ALL_STAFFS),
        CUSTOMER("customer", Model.PREDICATE_SHOW_ALL_CUSTOMERS),
        SUPPLIER("supplier", Model.PREDICATE_SHOW_ALL_SUPPLIERS);

        private final String keyword;
        private final Predicate<Person> predicate;

        Category(String keyword, Predicate<Person> predicate) {
            this.keyword = keyword;
            this.predicate = predicate;
        }

        private Predicate<Person> getPredicate() {
            return predicate;
        }

        public static Category fromString(String input) {
            String trimmedToLowerCaseInput = input.trim().toLowerCase();
            for (Category category : values()) {
                if (category.keyword.equals(trimmedToLowerCaseInput)) {
                    return category;
                }
            }

            return null;
        }
    }

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List <all | staff | customer | supplier> categories";
    public static final String MESSAGE_SUCCESS_FORMAT = "Listed %s";

    private final Category category;

    public ListCommand(Category category) {
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(category.getPredicate());
        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, this.category.toString().toLowerCase()));
    }
}
