package kuya.talent.attendence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long studentId;

    @ElementCollection
    @CollectionTable(name = "talents", joinColumns = @JoinColumn(name = "attendance_id"))
    private List<Talent> talents = new ArrayList<>();

    @Column
    private LocalDateTime createdAt;


}
