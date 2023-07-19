package com.hexagonal.template.application.usecase;

import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.domain.service.BookService;
import com.hexagonal.template.domain.usecase.GetBookUseCase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetBookUseCaseImpl implements GetBookUseCase {

  private final @NonNull BookService bookService;
  
  @Override
  public Book getBookById(Long id) {
    return this.bookService.getBookById(id);
  }
}
