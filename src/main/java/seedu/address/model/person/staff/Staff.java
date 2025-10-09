package seedu.address.model.person.staff;

import java.util.List;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a staff in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Staff extends Person {
    private List<Shift> shifts;

    /**
     * Constructor that calls super class constructor and initialise other relevant fields
     */
    public Staff(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Shift shift) {
        super(name, phone, email, address, tags);
        shifts.add(shift);
    }
}
