package com.hexagonal.template.domain.usecase;

import com.hexagonal.template.domain.model.entity.Book;

public interface UpdateBookUseCase {
 
  Book updateBook(Book book);
  
}
