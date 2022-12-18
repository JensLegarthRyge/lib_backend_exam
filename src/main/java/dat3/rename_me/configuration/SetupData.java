package dat3.rename_me.configuration;

import dat3.rename_me.entity.Book;
import dat3.rename_me.entity.Member;
import dat3.rename_me.repository.BookRepository;
import dat3.rename_me.repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SetupData implements ApplicationRunner {

    BookRepository bookRepository;
    MemberRepository memberRepository;

    public SetupData(BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<Book> books = List.of(
                Book.builder()
                        .isbnNumber(9788771372213L)
                        .title("The Hitchhiker's Guide to the Galaxy")
                        .author("Douglas Adams")
                        .publisher("Pan Books")
                        .publishYear(1979L)
                        .build(),
                Book.builder()
                        .isbnNumber(9780345391803L)
                        .title("The Restaurant at the End of the Universe")
                        .author("Douglas Adams")
                        .publisher("Pan Books")
                        .publishYear(1980L)
                        .build(),
                Book.builder()
                        .isbnNumber(9780345391810L)
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


    }
}
