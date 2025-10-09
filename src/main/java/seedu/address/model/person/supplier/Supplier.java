package seedu.address.model.person.supplier;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Represents a supplier in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Supplier extends Person {

    public Supplier(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return "Supplier: " + super.toString();
    }
}