package com.hexagonal.template.api.controller;

import java.util.List;

import com.hexagonal.template.api.mapper.RestBooksMapper;
import com.hexagonal.template.domain.model.dto.BookDto;
import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.domain.usecase.AddBookUseCase;
import com.hexagonal.template.domain.usecase.DeleteBookUseCase;
import com.hexagonal.template.domain.usecase.GetBookUseCase;
import com.hexagonal.template.domain.usecase.GetBooksUseCase;
import com.hexagonal.template.domain.usecase.UpdateBookUseCase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

  private final @NonNull GetBooksUseCase getBooksUseCase;

  private final @NonNull GetBookUseCase getBookUseCase;

  private final @NonNull AddBookUseCase addBookUseCase;

  private final @NonNull UpdateBookUseCase updateBookUseCase;

  private final @NonNull DeleteBookUseCase deleteBookUseCase;

  private final @NonNull RestBooksMapper restBooksMapper;

  @GetMapping("/books")
  public ResponseEntity<List<BookDto>> getBooks() {

    List<Book> books = getBooksUseCase.getBooks();

    List<BookDto> bookDtos = restBooksMapper.toDtos(books);

    return ResponseEntity.ok(bookDtos);
  }

  @GetMapping("/books/{id}")
  public ResponseEntity<BookDto> getBookById(@PathVariable long id) {
    Book book = getBookUseCase.getBookById(id);

    if (book == null) {
      return ResponseEntity.notFound().build();
    } else {
      BookDto bookDto = restBooksMapper.toDto(book);
      return ResponseEntity.ok(bookDto);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {

    Book book = addBookUseCase.saveBook(restBooksMapper.toEntity(bookDto));

    return ResponseEntity.ok(restBooksMapper.toDto(book));
  }

  @PutMapping("/update")
  public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {

    Book book = updateBookUseCase.updateBook(restBooksMapper.toEntity(bookDto));

    return  ResponseEntity.ok(restBooksMapper.toDto(book)); 
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteBookById(@PathVariable long id) {

    deleteBookUseCase.deleteBook(id);

    return ResponseEntity.ok().build();
  }

}
