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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetBookUseCaseImplTest {

    @Mock
    BookService bookService;

    @InjectMocks
    GetBookUseCaseImpl useCase;

    BookData bookData;

    @BeforeEach
    void setUp() {
        this.bookData = new BookData();
    }

    @Test
    void getBooks() {
        final Book book = this.bookData.get(1);

        when(this.bookService.getBookById(anyLong())).thenReturn(book);
        final Book result = this.useCase.getBookById(1L);

        assertNotNull(result);
        assertEquals(book, result);
        verify(this.bookService).getBookById(anyLong());
    }
}