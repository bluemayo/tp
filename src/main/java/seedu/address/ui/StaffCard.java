package seedu.address.ui;

import java.awt.*;

import javafx.fxml.FXML;
import seedu.address.model.person.Person;

public class StaffCard extends PersonCard {

    private static final String FXML = "StaffListCard.fxml";

    @FXML
    protected Label shifts;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public StaffCard(Person person, int displayedIndex) {
        super(person, displayedIndex, FXML);
        type.getStyleClass().add("type_staff");
        shifts.setText(person.getShifts().toString());
    }
}

