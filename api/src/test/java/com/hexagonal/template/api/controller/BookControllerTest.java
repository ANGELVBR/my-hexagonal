package com.hexagonal.template.api.controller;

import com.hexagonal.template.api.data.BookDtoData;
import com.hexagonal.template.api.mapper.RestBooksMapper;
import com.hexagonal.template.application.usecase.AddBookUseCaseImpl;
import com.hexagonal.template.domain.data.BookData;
import com.hexagonal.template.domain.model.dto.BookDto;
import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.domain.service.BookService;
import com.hexagonal.template.domain.usecase.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    GetBooksUseCase getBooksUseCase;

    @Mock
    GetBookUseCase getBookUseCase;

    @Mock
    AddBookUseCase addBookUseCase;

    @Mock
    UpdateBookUseCase updateBookUseCase;

    @Mock
    DeleteBookUseCase deleteBookUseCase;

    @Mock
    RestBooksMapper restBooksMapper;

    @InjectMocks
    BookController controller;

    BookData bookData;
    BookDtoData bookDtoData;

    @BeforeEach
    void setUp() {
        
        this.bookData = new BookData();
        this.bookDtoData = new BookDtoData();
    }
    
    @Test
    void getBooks() {
        final List<Book> bookList = this.bookData.getList();
        final List<BookDto> bookDtoList = this.bookDtoData.getList();

        when(this.getBooksUseCase.getBooks()).thenReturn(bookList);
        when(this.restBooksMapper.toDtos(anyList())).thenReturn(bookDtoList);
        final ResponseEntity<List<BookDto>> result = this.controller.getBooks();

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(bookList.size(), result.getBody().size());
        
        verify(this.getBooksUseCase).getBooks();
        verify(this.restBooksMapper).toDtos(anyList());
   }

    @Test
    void getBookById() {
        final Book book = this.bookData.get(1);
        final BookDto bookDto = this.bookDtoData.get(1);

        when(this.getBookUseCase.getBookById(anyLong())).thenReturn(book);
        when(this.restBooksMapper.toDto(any())).thenReturn(bookDto);
        final ResponseEntity<BookDto> result = this.controller.getBookById(1);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(book.getId(), result.getBody().getId());
        assertEquals(book.getTitle(), result.getBody().getTitle());
        assertEquals(book.getAuthor(), result.getBody().getAuthor());
        assertEquals(book.getIsbn(), result.getBody().getIsbn());

        verify(this.getBookUseCase).getBookById(anyLong());
        verify(this.restBooksMapper).toDto(any());
    }

    @Test
    void getBookById_notContent() {

        when(this.getBookUseCase.getBookById(anyLong())).thenReturn(null);
        final ResponseEntity<BookDto> result = this.controller.getBookById(1);

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

        verify(this.getBookUseCase).getBookById(anyLong());
        verify(this.restBooksMapper, never()).toDto(any());
    }

    @Test
    void addBook() {
        final Book book = this.bookData.get(1);
        final BookDto bookDto = this.bookDtoData.get(1);

        when(this.restBooksMapper.toEntity(any())).thenReturn(book);
        when(this.addBookUseCase.saveBook(any())).thenReturn(book);
        when(this.restBooksMapper.toDto(any())).thenReturn(bookDto);
        final ResponseEntity<BookDto> result = this.controller.addBook(bookDto);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(book.getId(), result.getBody().getId());
        assertEquals(book.getTitle(), result.getBody().getTitle());
        assertEquals(book.getAuthor(), result.getBody().getAuthor());
        assertEquals(book.getIsbn(), result.getBody().getIsbn());

        verify(this.addBookUseCase).saveBook(any());
        verify(this.restBooksMapper).toDto(any());
        verify(this.restBooksMapper).toEntity(any());
    }

    @Test
    void updateBook() {
        final Book book = this.bookData.get(1);
        final BookDto bookDto = this.bookDtoData.get(1);

        when(this.restBooksMapper.toEntity(any())).thenReturn(book);
        when(this.updateBookUseCase.updateBook(any())).thenReturn(book);
        when(this.restBooksMapper.toDto(any())).thenReturn(bookDto);
        final ResponseEntity<BookDto> result = this.controller.updateBook(bookDto);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(book.getId(), result.getBody().getId());
        assertEquals(book.getTitle(), result.getBody().getTitle());
        assertEquals(book.getAuthor(), result.getBody().getAuthor());
        assertEquals(book.getIsbn(), result.getBody().getIsbn());

        verify(this.updateBookUseCase).updateBook(any());
        verify(this.restBooksMapper).toDto(any());
        verify(this.restBooksMapper).toEntity(any());
    }

    @Test
    void deleteBookById() {

        doNothing().when(this.deleteBookUseCase).deleteBook(anyLong());
    
        final ResponseEntity<Void> result = this.controller.deleteBookById(1);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(this.deleteBookUseCase).deleteBook(anyLong());
    }
}