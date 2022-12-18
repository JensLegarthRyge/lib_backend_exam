package dat3.rename_me.configuration;

import dat3.rename_me.entity.Book;
import dat3.rename_me.entity.Loan;
import dat3.rename_me.entity.Member;
import dat3.rename_me.entity.Reservation;
import dat3.rename_me.repository.BookRepository;
import dat3.rename_me.repository.LoanRepository;
import dat3.rename_me.repository.MemberRepository;
import dat3.rename_me.repository.ReservationRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SetupData implements ApplicationRunner {

    BookRepository bookRepository;
    MemberRepository memberRepository;
    LoanRepository loanRepository;
    ReservationRepository reservationRepository;

    public SetupData(BookRepository bookRepository,
                     MemberRepository memberRepository,
                     LoanRepository loanRepository,
                     ReservationRepository reservationRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<Book> books = List.of(
                Book.builder()
                        .ISBNNumber(9788771372213L)
                        .title("The Hitchhiker's Guide to the Galaxy")
                        .author("Douglas Adams")
                        .publisher("Pan Books")
                        .publishYear(1979L)
                        .build(),
                Book.builder()
                        .ISBNNumber(9780345391803L)
                        .title("The Restaurant at the End of the Universe")
                        .author("Douglas Adams")
                        .publisher("Pan Books")
                        .publishYear(1980L)
                        .build(),
                Book.builder()
                        .ISBNNumber(9780345391810L)
                        .title("Life, the Universe and Everything")
                        .author("Douglas Adams")
                        .publisher("Pan Books")
                        .publishYear(1982L)
                        .build()
        );
        bookRepository.saveAll(books);

        List<Member> members = List.of(
                Member.builder()
                        .userName("user1")
                        .password("password1")
                        .email("ab@cd.dk")
                        .build(),
                Member.builder()
                        .userName("user2")
                        .password("password2")
                        .email("ef@gh.dk")
                        .build()
        );
        memberRepository.saveAll(members);

        List<Loan> loans = List.of(Loan.builder()
                .checkoutDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .book(bookRepository.findAll().get(0))
                .member(memberRepository.findAll().get(0))
                .build(),
                Loan.builder()
                .checkoutDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .book(bookRepository.findAll().get(0))
                .member(memberRepository.findAll().get(1))
                .build(),
                Loan.builder()
                .checkoutDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .book(bookRepository.findAll().get(1))
                .member(memberRepository.findAll().get(1))
                .build()
        );
        loanRepository.saveAll(loans);

        List<Reservation> reservations = List.of(
                Reservation.builder()
                        .reservationDate(LocalDate.now().plusDays(2))
                        .book(bookRepository.findAll().get(0))
                        .member(memberRepository.findAll().get(1))
                        .build(),
                Reservation.builder()
                        .reservationDate(LocalDate.now().plusDays(3))
                        .book(bookRepository.findAll().get(1))
                        .member(memberRepository.findAll().get(0))
                        .build()
        );
        reservationRepository.saveAll(reservations);








    }
}
