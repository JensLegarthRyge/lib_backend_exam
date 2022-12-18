package dat3.rename_me.dto;

import dat3.rename_me.entity.Book;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BookResponse {
    private Long id;
    private Long ISBNNumber;
    private String title;
    private String author;
    private String publisher;
    private Long publishYear;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.ISBNNumber = book.getISBNNumber();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.publishYear = book.getPublishYear();
    }
}
