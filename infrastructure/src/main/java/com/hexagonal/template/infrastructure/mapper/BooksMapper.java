package com.hexagonal.template.infrastructure.mapper;

import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.infrastructure.entity.BookJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BooksMapper {

  Book toEntity(BookJpa src);

  BookJpa toModel(Book src);

}
