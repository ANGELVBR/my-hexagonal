package com.hexagonal.template.api.mapper;


import com.hexagonal.template.domain.model.dto.BookDto;
import com.hexagonal.template.domain.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestBooksMapper {

  BookDto toDto(Book src);

  List<BookDto> toDtos(List<Book> src);
  
  Book toEntity(BookDto src);

}
