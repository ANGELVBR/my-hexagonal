package com.hexagonal.template.application.usecase;

import com.hexagonal.template.domain.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteBookUseCaseImplTest {

    @Mock
    BookService bookService;

    @InjectMocks
    DeleteBookUseCaseImpl useCase;
    
    @Test
    void deleteBook() {

        doNothing().when(this.bookService).deleteBook(any());
        this.useCase.deleteBook(1L);

        verify(this.bookService).deleteBook(any());
    }
}