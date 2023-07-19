package com.hexagonal.template.infrastructure.service;

import java.util.List;

import com.hexagonal.template.domain.constants.Errors;
import com.hexagonal.template.domain.exception.ValidationException;
import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.domain.repository.BookRepository;
import com.hexagonal.template.domain.service.BookService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BooksServiceImpl implements BookService {

  private final @NonNull BookRepository bookRepository;

  @Override

  public Book getBookById(Long bookId) {
    return bookRepository.getBookById(bookId);
  }

  @Override
  public List<Book> getBooks() {
    return bookRepository.getBooks();
  }

  @Override
  @Transactional
  public Book saveBook(Book book) {
    return bookRepository.saveBook(book);
  }

  @Override
  @Transactional
  public Book updateBook(Book book) {
    Book bookById = getBookById(book.getId());
    
    if (bookById != null) {
      book.setId(bookById.getId());
      bookRepository.updateBook(book);
    } else {
      throw new ValidationException(Errors.ERROR_FORBIDDEN);
    }
    return book;
  }

  @Override
  @Transactional
  public void deleteBook(Long bookId) {
    Book bookById = getBookById(bookId);
    if (bookById != null) {
      bookRepository.deleteBook(bookId);
    } else {
      throw new ValidationException(Errors.ERROR_FORBIDDEN);
    }
  }
}
