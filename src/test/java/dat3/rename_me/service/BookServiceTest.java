package dat3.rename_me.service;

import dat3.rename_me.dto.BookRequest;
import dat3.rename_me.dto.BookResponse;
import dat3.rename_me.entity.Book;
import dat3.rename_me.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookServiceTest {
    BookService bookService;

    static BookRepository bookRepository;

    @BeforeAll
    public static void setUpRepos(@Autowired BookRepository book_Repository){
        bookRepository = book_Repository;

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
    }

    @BeforeEach
    void setUpService() {
        bookService = new BookService(bookRepository);
    }

    @Test
    void createBook() {
        assertEquals(3, bookRepository.findAll().size());
        BookRequest bookRequest = BookRequest.builder()
                .ISBNNumber(123456789L)
                .title("Test")
                .author("Test")
                .publisher("Test")
                .publishYear(2020L)
                .build();
        bookService.createBook(bookRequest);
        assertEquals(4, bookRepository.findAll().size());
    }

    @Test
    void readBookById() {
        BookResponse bookToRead = bookService.readBookById(1L);
        assertEquals(9788771372213L, bookToRead.getISBNNumber());
        assertNotEquals(9780345391803L, bookToRead.getISBNNumber());
        assertEquals(1L, bookToRead.getId());
    }

    @Test
    void readAllBooks() {
        List<BookResponse> books = bookService.readAllBooks();
        assertEquals(3, books.size());
        assertEquals(9788771372213L, books.get(0).getISBNNumber());
        assertEquals(9780345391803L, books.get(1).getISBNNumber());
        assertEquals(9780345391810L, books.get(2).getISBNNumber());
    }

    @Test
    void updateBook() {
        BookRequest bookRequestISBN = BookRequest.builder()
                .id(1L)
                .ISBNNumber(7357L)
                .build();
        bookService.updateBook(bookRequestISBN);
        assertEquals(7357L, bookRepository.findById(1L).get().getISBNNumber());
        assertNotNull(bookRepository.findById(1L).get().getAuthor());

        BookRequest bookRequestTitle = BookRequest.builder()
                .id(1L)
                .title("TestTitle")
                .build();
        bookService.updateBook(bookRequestTitle);
        assertEquals("TestTitle", bookRepository.findById(1L).get().getTitle());
        assertNotNull(bookRepository.findById(1L).get().getISBNNumber());

        BookRequest bookRequestAuthor = BookRequest.builder()
                .id(1L)
                .author("TestAuthor")
                .build();
        bookService.updateBook(bookRequestAuthor);
        assertEquals("TestAuthor", bookRepository.findById(1L).get().getAuthor());
        assertNotNull(bookRepository.findById(1L).get().getTitle());

        BookRequest bookRequestPublisher = BookRequest.builder()
                .id(1L)
                .publisher("TestPublisher")
                .build();
        bookService.updateBook(bookRequestPublisher);
        assertEquals("TestPublisher", bookRepository.findById(1L).get().getPublisher());
        assertNotNull(bookRepository.findById(1L).get().getPublishYear());

        BookRequest bookRequestPublishYear = BookRequest.builder()
                .id(1L)
                .publishYear(7357L)
                .build();
        bookService.updateBook(bookRequestPublishYear);
        assertEquals(7357L, bookRepository.findById(1L).get().getPublishYear());
        assertNotNull(bookRepository.findById(1L).get().getPublisher());

        BookRequest bookRequestAll = BookRequest.builder()
                .id(2L)
                .ISBNNumber(22222L)
                .title("TWO")
                .author("TWO")
                .publisher("TWO")
                .publishYear(22222L)
                .build();
        bookService.updateBook(bookRequestAll);
        assertEquals(22222L, bookRepository.findById(2L).get().getISBNNumber());
        assertEquals("TWO", bookRepository.findById(2L).get().getTitle());
        assertEquals("TWO", bookRepository.findById(2L).get().getAuthor());
        assertEquals("TWO", bookRepository.findById(2L).get().getPublisher());
        assertEquals(22222L, bookRepository.findById(2L).get().getPublishYear());
    }

    @Test
    void deleteBook() {
        assertEquals(3, bookRepository.findAll().size());
        bookService.deleteBook(1L);
        assertEquals(2, bookRepository.findAll().size());
        assertFalse(bookRepository.findById(1L).isPresent());
    }
}