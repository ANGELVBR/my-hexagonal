package com.hexagonal.template.domain.usecase;

import com.hexagonal.template.domain.model.entity.Book;
import java.util.List;

public interface GetBooksUseCase {
  List<Book> getBooks();
        
}
