package seedu.address.model.person.supplier;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Represents a supplier in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Supplier extends Person {

    private final List<String> items;
    private final EnumSet<DayOfWeek> days;
    private final String notes;

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

    /** Unmodifiable view of items supplied. */
    public List<String> getItems() {
        return Collections.unmodifiableList(items);
    }

    /** Unmodifiable view of delivery days. */
    public Set<DayOfWeek> getDays() {
        return Collections.unmodifiableSet(days);
    }

    /** Optional notes (may be empty string). */
    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Supplier: " + super.toString()
                + " items:" + items
                + " days:" + days
                + (notes.isEmpty() ? "" : "; notes:" + notes);
    }

}