package com.hexagonal.template.application.usecase;

import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.domain.service.BookService;
import com.hexagonal.template.domain.usecase.UpdateBookUseCase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateBookUseCaseImpl implements UpdateBookUseCase {

  private final @NonNull BookService bookService;
    
  @Override
  public Book updateBook(Book book) {
    return this.bookService.updateBook(book);
  }
  
}
