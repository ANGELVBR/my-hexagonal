package com.hexagonal.template.infrastructure.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "BOOK")
public class BookJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "TITLE", nullable = false)
  private String title;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "PRICE", nullable = false)
  private Double price;

  @Column(name = "AUTHOR", nullable = false)
  private String author;

  @Column(name = "ISBN", nullable = false)
  private String isbn;

  @Column(name = "LANGUAGE", nullable = false)
  private String language;

  @Column(name = "PUBLISHER", nullable = false)
  private String publisher;

  @Column(name = "GENRE", nullable = false)
  private String genre;

  @Column(name = "FORMAT")
  private String format;

  @Column(name = "PAGES")
  private Integer pages;

  @Temporal(TemporalType.DATE)
  @Column(name = "CREATED_DATE", nullable = false)
  private Date createdDate;

}
