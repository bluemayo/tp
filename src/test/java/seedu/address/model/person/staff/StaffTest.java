package seedu.address.model.person.staff;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class StaffTest {
    private Name validName = new Name(PersonBuilder.DEFAULT_NAME);
    private Phone validPhone = new Phone(PersonBuilder.DEFAULT_PHONE);
    private Email validEmail = new Email(PersonBuilder.DEFAULT_EMAIL);
    private Address validAddress = new Address(PersonBuilder.DEFAULT_ADDRESS);
    private Set<Tag> tags = new HashSet<>();
    private Note validNote = new Note("");
    private List<Shift> shifts;

    @BeforeEach
    public void setUp() {
        shifts = new ArrayList<>();
        shifts.add(new Shift(LocalDate.of(2025, 10, 15)));
        shifts.add(new Shift(LocalDate.of(2025, 10, 20)));
    }

    @Test
    public void constructor_validInputs_success() {
        Staff staff = new Staff(validName, validPhone, validEmail, validAddress, tags, shifts, validNote);

        assertEquals(validName, staff.getName());
        assertEquals(validPhone, staff.getPhone());
        assertEquals(validEmail, staff.getEmail());
        assertEquals(validAddress, staff.getAddress());
        assertEquals(tags, staff.getTags());
        assertEquals(shifts, staff.getShifts());
    }

    @Test
    public void constructor_defensiveCopy_shiftsListNotAliased() {
        Staff staff = new Staff(validName, validPhone, validEmail, validAddress, tags, shifts, validNote);

        shifts.add(new Shift(LocalDate.of(2025, 11, 1)));

        assertEquals(2, staff.getShifts().size());
    }

    @Test
    public void getShifts_returnsImmutableList() {
        Staff staff = new Staff(validName, validPhone, validEmail, validAddress, tags, shifts, validNote);

        List<Shift> staffShifts = staff.getShifts();
        assertThrows(UnsupportedOperationException.class, () -> staffShifts.add(new Shift(LocalDate.now())));
    }
}
