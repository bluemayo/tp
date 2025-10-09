package seedu.address.model.person.staff;

import java.time.Duration;
import java.time.LocalDateTime;

public class Shift {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Shift(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }

    @Override
    public String toString() {
        return "From: " + startTime.toString() + "\n" + "To: " + endTime.toString();
    }
}

