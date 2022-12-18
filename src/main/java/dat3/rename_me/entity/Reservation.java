package dat3.rename_me.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate reservationDate;
    @ManyToOne
    @JoinTable(name="member_reservation")
    private Member member;

    @ManyToOne
    @JoinTable(name="book_reservation")
    private Book book;
}
