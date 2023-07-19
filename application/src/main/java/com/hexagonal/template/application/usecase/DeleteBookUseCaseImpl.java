package com.hexagonal.template.application.usecase;

import com.hexagonal.template.domain.service.BookService;
import com.hexagonal.template.domain.usecase.DeleteBookUseCase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteBookUseCaseImpl implements DeleteBookUseCase {

  private final @NonNull BookService bookService;

  @Override
  public void deleteBook(Long bookId) {
    this.bookService.deleteBook(bookId);
  }
}
