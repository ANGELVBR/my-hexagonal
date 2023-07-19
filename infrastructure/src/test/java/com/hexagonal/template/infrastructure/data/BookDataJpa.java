package com.hexagonal.template.infrastructure.data;

import com.hexagonal.template.domain.model.entity.Book;
import com.hexagonal.template.infrastructure.entity.BookJpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class BookDataJpa {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, BookJpa> dataMap;

  public BookDataJpa() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final BookJpa item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private BookJpa generate(final Integer i) {
    final long id = this.keyGenerator.getAndIncrement();

    BookJpa bookJpa = new BookJpa();
    bookJpa.setId(id);
    bookJpa.setTitle("TITLE_" + id);
    bookJpa.setDescription("DESCRIPTION_" + id);
    bookJpa.setPrice(19.99);
    bookJpa.setAuthor("AUTHOR_" + id);
    bookJpa.setIsbn("111-1-111111-11-0");
    bookJpa.setLanguage("LANGUAGE_" + id);
    bookJpa.setPublisher("PUBLISHER_" + id);
    bookJpa.setGenre("GENRE_" + id);
    bookJpa.setFormat("FORMAT_" + id);
    bookJpa.setPages(250);
    bookJpa.setCreatedDate(new Date());
    
    return bookJpa;
  }

  public BookJpa get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<BookJpa> getList() {

    final List<BookJpa> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
