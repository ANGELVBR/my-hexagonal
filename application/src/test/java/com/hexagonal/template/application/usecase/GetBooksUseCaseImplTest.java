package com.hexagonal.template.application.usecase;

import com.hexagonal.template.domain.data.BookData;
import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.domain.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetBooksUseCaseImplTest {

    @Mock
    BookService bookService;

    @InjectMocks
    GetBooksUseCaseImpl useCase;

    BookData bookData;

    @BeforeEach
    void setUp() {
        this.bookData = new BookData();
    }
    
    @Test
    void getBooks() {
        final List<Book> bookList = this.bookData.getList();

        when(this.bookService.getBooks()).thenReturn(bookList);
        final List<Book> result = this.useCase.getBooks();

        assertNotNull(result);
        assertEquals(bookList, result);
        verify(this.bookService).getBooks();
    }
}