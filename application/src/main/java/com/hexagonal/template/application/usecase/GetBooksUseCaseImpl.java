package com.hexagonal.template.application.usecase;

import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.domain.service.BookService;
import com.hexagonal.template.domain.usecase.GetBooksUseCase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetBooksUseCaseImpl implements GetBooksUseCase { 
    
  private final @NonNull BookService bookService;
    
  @Override
  public List<Book> getBooks() {
    return this.bookService.getBooks();
  }
  
}
