package kuya.talent.attendence;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Talent {
    private int amount;
    private LocalDateTime createdAt;
}
