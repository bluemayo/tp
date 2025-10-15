package seedu.address.model.person.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class CustomerTest {

    // Valid fields
    private Name validName = new Name(PersonBuilder.DEFAULT_NAME);
    private Phone validPhone = new Phone(PersonBuilder.DEFAULT_PHONE);
    private Email validEmail = new Email(PersonBuilder.DEFAULT_EMAIL);
    private Address validAddress = new Address(PersonBuilder.DEFAULT_ADDRESS);
    private Set<Tag> tags = new HashSet<>();
    private Note note = new Note(PersonBuilder.DEFAULT_NOTE);

    @Test
    public void constructor_validFields_createsCustomer() {
        Customer customer = new Customer(validName, validPhone, validEmail, validAddress, tags, note);
        assertEquals(validName, customer.getName());
    }

    @Test
    void equals_sameFields_returnsTrue() {
        Customer customer1 = new Customer(validName, validPhone, validEmail, validAddress, tags, note);
        Customer customer2 = new Customer(validName, validPhone, validEmail, validAddress, tags, note);
        assertTrue(customer1.equals(customer2));
    }


}
