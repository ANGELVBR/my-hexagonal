package com.hexagonal.template.domain.model.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  private Long id;

  private String title;

  private String description;

  private Double price;

  private String author;

  private String isbn;

  private String language;

  private String publisher;

  private String genre;

  private String format;

  private Integer pages;

  private Date createdDate;
}






