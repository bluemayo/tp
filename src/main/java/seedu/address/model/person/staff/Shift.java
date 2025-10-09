package seedu.address.model.person.staff;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Represents a staff's shift in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Shift {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor that creates Shift class with startTime and endTime.
     */
    public Shift(LocalDateTime startTime, LocalDateTime endTime) {
        requireAllNonNull(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns an immutable Duration between startTime and endTime.
     */
    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }

    @Override
    public String toString() {
        return "From: " + startTime.toString() + "\n" + "To: " + endTime.toString();
    }
}

