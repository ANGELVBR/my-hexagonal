package com.hexagonal.template.infrastructure.repository;

import com.hexagonal.template.domain.data.BookData;
import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.infrastructure.data.BookDataJpa;
import com.hexagonal.template.infrastructure.entity.BookJpa;
import com.hexagonal.template.infrastructure.mapper.BooksMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookRepositoryImplTest {

    @Mock
    BookRepositoryJpa bookRepositoryJpa;
    @Mock
    BooksMapper booksMapper;

    @InjectMocks
    BookRepositoryImpl repository;

    BookData bookData;

    BookDataJpa bookDataJpa;

    @BeforeEach
    void setUp() {
        this.bookData = new BookData();
        this.bookDataJpa = new BookDataJpa();
    }

    @Test
    @DisplayName("Test find book by id")
    void getBookById() {
        final Book book = this.bookData.get(1);
        final BookJpa bookJpa = this.bookDataJpa.get(1);

        when(this.bookRepositoryJpa.findById(anyLong())).thenReturn(Optional.of(bookJpa));
        when(this.booksMapper.toEntity(any())).thenReturn(book);
        final Book result = this.repository.getBookById(1L);

        assertNotNull(result);
        assertEquals(book, result);
        verify(this.bookRepositoryJpa).findById(anyLong());
        verify(this.booksMapper).toEntity(any());
    }

    @Test
    @DisplayName("Test find all books")
    void getBooks() {
        final Book book = this.bookData.get(1);
        final List<Book> bookList = this.bookData.getList();
        final List<BookJpa> bookJpaList = this.bookDataJpa.getList();

        when(this.bookRepositoryJpa.findAll()).thenReturn(bookJpaList);
        when(this.booksMapper.toEntity(any())).thenReturn(book);
        final List<Book> result = this.repository.getBooks();

        assertNotNull(result);
        assertEquals(bookList.size(), result.size());
        assertEquals(bookList.get(0), result.get(0));
        verify(this.bookRepositoryJpa).findAll();
    }

    @Test
    @DisplayName("Test find book by id")
    void saveBook() {
        final Book book = this.bookData.get(1);
        final BookJpa bookJpa = this.bookDataJpa.get(1);

        when(this.booksMapper.toModel(any())).thenReturn(bookJpa);
        when(this.bookRepositoryJpa.save(any())).thenReturn(bookJpa);
        when(this.booksMapper.toEntity(any())).thenReturn(book);
        final Book result = this.repository.saveBook(book);

        assertNotNull(result);
        assertEquals(book, result);
        verify(this.booksMapper).toModel(any());
        verify(this.bookRepositoryJpa).save(any());
        verify(this.booksMapper).toEntity(any());
    }

    @Test
    @DisplayName("Test update book that exists")
    void updateBook() {
        final Book book = this.bookData.get(1);
        final BookJpa bookJpa = this.bookDataJpa.get(1);

        when(this.booksMapper.toModel(any())).thenReturn(bookJpa);
        when(this.bookRepositoryJpa.save(any())).thenReturn(bookJpa);
        when(this.booksMapper.toEntity(any())).thenReturn(book);
        final Book result = this.repository.updateBook(book);

        assertNotNull(result);
        assertEquals(book, result);
        verify(this.booksMapper).toModel(any());
        verify(this.bookRepositoryJpa).save(any());
        verify(this.booksMapper).toEntity(any());
    }


    @Test
    @DisplayName("Test delete book by id")
    void deleteBook() {
        final Book book = this.bookData.get(1);
        final BookJpa bookJpa = this.bookDataJpa.get(1);

        doNothing().when(this.bookRepositoryJpa).deleteById(anyLong());
        this.repository.deleteBook(1L);

        verify(this.bookRepositoryJpa).deleteById(anyLong());
    }
   
}