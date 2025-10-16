package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {
    // Customers
    public static final Person ALICE = new CustomerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").build();
    public static final Person BENSON = new CustomerBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("frequentCustomer").withNote("allergic to nuts").build();
    public static final Person AMY = new CustomerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).build();

    // Staff
    public static final Person CARL;
    public static final Person DANIEL;

    static {
        try {
            CARL = new StaffBuilder().withName("Carl Kurz").withPhone("95352563")
                    .withEmail("heinz@example.com").withAddress("wall street").build();
            DANIEL = new StaffBuilder().withName("Daniel Meier").withPhone("87652533")
                    .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Failed to initialize Staff constants: " + e.getMessage());
        }
    }

    // Supplier
    public static final Person ELLE;
    public static final Person FIONA;

    static {
        try {
            ELLE = new SupplierBuilder().withName("Elle Meyer").withPhone("9482224")
                    .withEmail("werner@example.com").withAddress("michegan ave").build();
            FIONA = new SupplierBuilder().withName("Fiona Kunz").withPhone("9482427")
                    .withEmail("lydia@example.com").withAddress("little tokyo").build();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Failed to initialize Staff constants: " + e.getMessage());
        }
    }

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
