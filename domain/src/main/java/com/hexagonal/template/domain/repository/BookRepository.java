package com.hexagonal.template.domain.repository;

import com.hexagonal.template.domain.model.entity.Book;
import java.util.List;

public interface BookRepository { 
  Book getBookById(Long bookId);
    
  List<Book> getBooks();
    
  Book saveBook(Book book);
    
  void deleteBook(Long bookId);
    
  Book updateBook(Book book);
  
}
