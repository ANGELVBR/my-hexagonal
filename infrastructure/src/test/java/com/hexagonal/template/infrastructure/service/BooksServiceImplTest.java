package com.hexagonal.template.infrastructure.service;

import com.hexagonal.template.domain.constants.Errors;
import com.hexagonal.template.domain.data.BookData;
import com.hexagonal.template.domain.exception.ValidationException;
import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.domain.repository.BookRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BooksServiceImplTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BooksServiceImpl service;

    BookData bookData;

    @BeforeEach
    void setUp() {
        this.bookData = new BookData();
    }

    @Test
    @DisplayName("Test find book by id")
    void getBookById() {
        final Book book = this.bookData.get(1);

        when(this.bookRepository.getBookById(anyLong())).thenReturn(book);
        final Book result = this.service.getBookById(1L);

        assertNotNull(result);
        assertEquals(book, result);
        verify(this.bookRepository).getBookById(anyLong());
    }

    @Test
    @DisplayName("Test find all books")
    void getBooks() {
        final List<Book> bookList = this.bookData.getList();

        when(this.bookRepository.getBooks()).thenReturn(bookList);
        final List<Book> result = this.service.getBooks();

        assertNotNull(result);
        assertEquals(bookList, result);
        verify(this.bookRepository).getBooks();
    }

    @Test
    @DisplayName("Test find book by id")
    void saveBook() {
        final Book book = this.bookData.get(1);

        when(this.bookRepository.saveBook(any())).thenReturn(book);
        final Book result = this.service.saveBook(book);

        assertNotNull(result);
        assertEquals(book, result);
        verify(this.bookRepository).saveBook(any());
    }

    @Test
    @DisplayName("Test update book that exists")
    void updateBook() {
        final Book book = this.bookData.get(1);

        when(this.bookRepository.getBookById(anyLong())).thenReturn(book);
        when(this.bookRepository.updateBook(any())).thenReturn(book);
        final Book result = this.service.updateBook(book);

        assertNotNull(result);
        assertEquals(book, result);
        verify(this.bookRepository).getBookById(anyLong());
        verify(this.bookRepository).updateBook(any());
    }

    @Test
    @DisplayName("Test update book that not exists")
    void updateBookNotExists() {
        final Book book = this.bookData.get(1);

        when(this.bookRepository.getBookById(anyLong())).thenReturn(null);

        final ValidationException validationException = assertThrows(ValidationException.class, () -> {
            this.service.updateBook(book);
        });

        assertEquals(Errors.ERROR_FORBIDDEN, validationException.getMessage());
        verify(this.bookRepository).getBookById(anyLong());
        verify(this.bookRepository,never()).updateBook(any());
    }

    @Test
    @DisplayName("Test delete book by id")
    void deleteBook() {
        final Book book = this.bookData.get(1);

        when(this.bookRepository.getBookById(anyLong())).thenReturn(book);
        doNothing().when(this.bookRepository).deleteBook(anyLong());
        this.service.deleteBook(1L);

        verify(this.bookRepository).getBookById(anyLong());
        verify(this.bookRepository).deleteBook(anyLong());
    }

    @Test
    @DisplayName("Test delete book by id but not exists")
    void deleteBookNotExists() {

        when(this.bookRepository.getBookById(anyLong())).thenReturn(null);

        final ValidationException validationException = assertThrows(ValidationException.class, () -> {
            this.service.deleteBook(1L);
        });

        assertEquals(Errors.ERROR_FORBIDDEN, validationException.getMessage());
        verify(this.bookRepository).getBookById(anyLong());
        verify(this.bookRepository, never()).deleteBook(anyLong());
    }
    
    
}