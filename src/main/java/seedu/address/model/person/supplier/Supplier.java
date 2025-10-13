package seedu.address.model.person.supplier;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a supplier in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Supplier extends Person {

    private final List<String> items;
    private final EnumSet<DayOfWeek> days;
    private final String notes;

    /**
     * Constructs a Supplier by calling super class constructor and initialise other relevant fields
     */
    public Supplier(Name name,
                    Phone phone,
                    Email email,
                    Address address,
                    Set<Tag> tags,
                    List<String> items,
                    EnumSet<DayOfWeek> days,
                    String notes) {

        super(name, phone, email, address, tags);
        requireAllNonNull(name, phone, email, address, tags, items, days);
        this.items = List.copyOf(items);
        this.days = days.clone();
        this.notes = notes == null ? "" : notes.trim();
    }

    /**
     * Returns the list of items supplied by this supplier.
     */
    public List<String> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Returns the set of days on which this supplier is available.
     */
    public Set<DayOfWeek> getDays() {
        return Collections.unmodifiableSet(days);
    }

    @Override
    public String toString() {
        return "Supplier: " + super.toString()
                + " items:" + items
                + " days:" + days
                + (notes.isEmpty() ? "" : "; notes:" + notes);
    }

}
