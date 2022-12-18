package dat3.rename_me.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long isbnNumber;
    private String title;
    private String author;
    private String publisher;
    private Long publishYear;

    @OneToMany
    @JoinTable(name="book_loan")
    private List<Loan> loans;

    @OneToMany
    @JoinTable(name="book_reservation")
    private List<Reservation> reservations;
}
