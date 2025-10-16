package seedu.address.testutil;

import java.util.List;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.supplier.Days;
import seedu.address.model.person.supplier.Items;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.util.SampleDataUtil;

public class SupplierBuilder extends PersonBuilder {
    public static final String DEFAULT_ITEMS = CommandTestUtil.VALID_ITEMS_AMY;
    public static final String DEFAULT_DAYS = CommandTestUtil.VALID_DAYS_AMY;

    private List<Items> items;
    private List<Days> days;

    /**
     * Creates a {@code SupplierBuilder} with the default details.
     */
    public SupplierBuilder() throws ParseException {
        super();
        days = ParserUtil.parseDays(List.of(DEFAULT_DAYS));
        items = ParserUtil.parseItems(List.of(DEFAULT_ITEMS));
    }

    /**
     * Initializes the SupplierBuilder with the data of {@code supplierToCopy}.
     */
    public SupplierBuilder(Supplier supplierToCopy) {
        super(supplierToCopy);
        days = supplierToCopy.getDays();
        items = supplierToCopy.getItems();
    }

    public SupplierBuilder withDays(List<Days> days) throws ParseException {
        this.days = SampleDataUtil.getDaysList();
        return this;
    }

    public SupplierBuilder withItems(List<Items> items) throws ParseException {
        this.items = SampleDataUtil.getItemsList();
        return this;
    }

    /**
     * Builds a new {@code Supplier} by taking in the relevant fields and outputting an object.
     */
    public Supplier build() {
        return new Supplier(name, phone, email, address, tags, items, days, note);
    }
}
