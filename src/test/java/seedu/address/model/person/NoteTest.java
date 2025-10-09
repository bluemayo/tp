package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    private static final String VALID_NOTE = "valid";
    private static final String EMPTY_NOTE = "";
    private static final String LONG_NOTE = "x".repeat(201); // invalid (201 chars)

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_invalidNote_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Note(LONG_NOTE));
    }

    @Test
    public void isValidNote() {
        // null note
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        // invalid note
        assertFalse(Note.isValidNote(LONG_NOTE)); // length <= 200

        // valid notes
        assertTrue(Note.isValidNote(VALID_NOTE));
        assertTrue(Note.isValidNote(EMPTY_NOTE));
    }

    @Test
    public void equals() {
        Note note = new Note(VALID_NOTE);

        // same values -> returns true
        assertTrue(note.equals(new Note(VALID_NOTE)));

        // same object -> returns true
        assertTrue(note.equals(note));

        // null -> returns false
        assertFalse(note.equals(null));

        // different types -> returns false
        assertFalse(note.equals(5.0f));

        // different values -> returns false
        assertFalse(note.equals(new Note("Another valid note")));
    }
}

