package com.hexagonal.template.application.usecase;

import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.domain.service.BookService;
import com.hexagonal.template.domain.usecase.AddBookUseCase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddBookUseCaseImpl implements AddBookUseCase {
    
  private final @NonNull BookService bookService;
    
  @Override
  public Book saveBook(Book book) {
    return this.bookService.saveBook(book);
  }
    
}
