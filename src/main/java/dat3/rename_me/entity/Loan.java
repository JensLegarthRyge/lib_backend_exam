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
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    @ManyToOne
    @JoinTable(name="member_loan")
    private Member member;

    @ManyToOne
    @JoinTable(name="book_loan")
    private Book book;
}
