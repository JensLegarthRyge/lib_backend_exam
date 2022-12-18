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

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Loan> loans;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
