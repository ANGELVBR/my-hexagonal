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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateBookUseCaseImplTest {

    @Mock
    BookService bookService;

    @InjectMocks
    UpdateBookUseCaseImpl useCase;

    BookData bookData;

    @BeforeEach
    void setUp() {
        this.bookData = new BookData();
    }
    
    @Test
    void updateBook() {
        final Book book = this.bookData.get(1);

        when(this.bookService.updateBook(any())).thenReturn(book);
        final Book result = this.useCase.updateBook(book);

        assertNotNull(result);
        assertEquals(book, result);
        verify(this.bookService).updateBook(any());
    }
}