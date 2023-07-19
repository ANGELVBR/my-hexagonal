package com.hexagonal.template.domain.service;

import com.hexagonal.template.domain.model.entity.Book;
import java.util.List;

public interface BookService {
  Book getBookById(Long bookId);
        
  List<Book> getBooks();
        
  Book saveBook(Book book);
        
  Book updateBook(Book book);
        
  void deleteBook(Long bookId);
    
}
