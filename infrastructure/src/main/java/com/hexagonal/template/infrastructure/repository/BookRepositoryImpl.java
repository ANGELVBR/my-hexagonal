package com.hexagonal.template.infrastructure.repository;


import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.domain.repository.BookRepository;
import com.hexagonal.template.infrastructure.entity.BookJpa;
import com.hexagonal.template.infrastructure.mapper.BooksMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

  private final @NonNull BookRepositoryJpa bookRepositoryJpa;
  private final @NonNull BooksMapper booksMapper;
    
  @Override
  public Book getBookById(Long bookId) {

    return this.bookRepositoryJpa.findById(bookId)
        .map(this.booksMapper::toEntity)
        .orElse(null);
  }

  @Override
  public List<Book> getBooks() {

    return this.bookRepositoryJpa.findAll()
        .stream()
        .map(this.booksMapper::toEntity)
        .toList();
  }

  @Override
  public Book saveBook(Book book) {
    final BookJpa bookJpa = this.booksMapper.toModel(book);
    final BookJpa bookJpaBd = this.bookRepositoryJpa.save(bookJpa);
    return this.booksMapper.toEntity(bookJpaBd);
  }

  @Override
  public void deleteBook(Long bookId) {
    this.bookRepositoryJpa.deleteById(bookId);
  }

  @Override
  public Book updateBook(Book book) {
    return this.saveBook(book);
  }
  
}
