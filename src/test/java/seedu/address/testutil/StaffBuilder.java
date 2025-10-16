package seedu.address.testutil;

import java.util.List;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.staff.Shift;
import seedu.address.model.person.staff.Staff;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.util.SampleDataUtil;

public class StaffBuilder extends PersonBuilder {
    public static final String DEFAULT_SHIFTS = CommandTestUtil.VALID_SHIFTS_AMY;

    private List<Shift> shifts;

    /**
     * Creates a {@code StaffBuilder} with the default details.
     */
    public StaffBuilder() throws ParseException {
        super();
        shifts = ParserUtil.parseShifts(List.of(DEFAULT_SHIFTS));
    }

    /**
     * Initializes the StaffBuilder with the data of {@code staffToCopy}.
     */
    public StaffBuilder(Staff staffToCopy) {
        super(staffToCopy);
        shifts = staffToCopy.getShifts();
    }

    public StaffBuilder withShifts(List<Shift> shifts) throws ParseException {
        this.shifts = SampleDataUtil.getShiftList();
        return this;
    }

    /**
     * Builds a new {@code Staff} by taking in the relevant fields and outputting an object.
     */
    public Staff build() {
        return new Staff(name, phone, email, address, tags, shifts, note);
    }
}
