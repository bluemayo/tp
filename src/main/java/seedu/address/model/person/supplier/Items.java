package seedu.address.model.person.supplier;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import java.util.List;

public class Items {
    public static final String MESSAGE_COMPULSORY = "At least one item must be provided.";
    public static final String MESSAGE_CONSTRAINTS = "Item must be non empty, and contain only letters and numbers.";

    private final String name;

    public Items(String name) {
        requireAllNonNull(name);
        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.name = trimmed;
    }

    @Override
    public String toString() { return name; }
}
