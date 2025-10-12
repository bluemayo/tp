package seedu.address.model.person.staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a staff in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Staff extends Person {
    private List<Shift> shifts = new ArrayList<>();

    /**
     * Constructor that calls super class constructor and initialise other relevant fields
     */
    public Staff(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<Shift> shifts, Note notes) {
        super(name, phone, email, address, tags, notes);
        this.shifts = new ArrayList<>(shifts);
    }

    public List<Shift> getShifts() {
        return shifts;
    }
}
