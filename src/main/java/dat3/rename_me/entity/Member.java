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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String email;

    @OneToMany
    @JoinTable(name="member_loan")
    private List<Loan> loans;

    @OneToMany
    @JoinTable(name="member_reservation")
    private List<Reservation> reservations;
}
