package dat3.rename_me.dto;

import dat3.rename_me.entity.Book;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookRequest {
    private Long id;
    private Long ISBNNumber;
    private String title;
    private String author;
    private String publisher;
    private Long publishYear;

    public Book createBookEntity(BookRequest bookRequest) {
        return Book.builder()
                .id(bookRequest.getId())
                .ISBNNumber(bookRequest.getISBNNumber())
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .publisher(bookRequest.getPublisher())
                .publishYear(bookRequest.getPublishYear())
                .build();
    }
}
