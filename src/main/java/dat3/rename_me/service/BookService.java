package dat3.rename_me.service;

import dat3.rename_me.dto.BookRequest;
import dat3.rename_me.dto.BookResponse;
import dat3.rename_me.entity.Book;
import dat3.rename_me.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse createBook(BookRequest bookRequest) {
        Book bookToSave = bookRequest.createBookEntity(bookRequest);
        return new BookResponse(bookRepository.save(bookToSave));
    }

    public BookResponse readBookById(Long id) {
        Book foundBook = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        return new BookResponse(foundBook);
    }

    public List<BookResponse> readAllBooks(){
        return bookRepository.findAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    public BookResponse updateBook(BookRequest bookRequest){
        Book bookToUpdate = bookRepository.findById(bookRequest.getId()).orElseThrow(()-> new RuntimeException("Book not found"));
        if (bookRequest.getISBNNumber() != null) {
            bookToUpdate.setISBNNumber(bookRequest.getISBNNumber());
        }
        if (bookRequest.getTitle() != null) {
            bookToUpdate.setTitle(bookRequest.getTitle());
        }
        if (bookRequest.getAuthor() != null) {
            bookToUpdate.setAuthor(bookRequest.getAuthor());
        }
        if (bookRequest.getPublisher() != null) {
            bookToUpdate.setPublisher(bookRequest.getPublisher());
        }
        if (bookRequest.getPublishYear() != null) {
            bookToUpdate.setPublishYear(bookRequest.getPublishYear());
        }
        return new BookResponse(bookRepository.save(bookToUpdate));
    }

    public BookResponse deleteBook(Long id){
        Book bookToDelete = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        bookRepository.delete(bookToDelete);
        return new BookResponse(bookToDelete);
    }

}
