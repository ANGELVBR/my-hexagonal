package com.hexagonal.template.domain.data;

import com.hexagonal.template.domain.model.entity.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class BookData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, Book> dataMap;

  public BookData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final Book item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private Book generate(final Integer i) {
    final long id = this.keyGenerator.getAndIncrement();
    return Book.builder()
            .id(id)
            .title("TITLE_" + id)
            .description("DESCRIPTION_" + id)
            .price(19.99)
            .author("AUTHOR_" + id)
            .isbn("111-1-111111-11-0")
            .language("LANGUAGE_" + id)
            .publisher("PUBLISHER_" + id)
            .genre("GENRE_" + id)
            .format("FORMAT_" + id)
            .pages(250)
            .createdDate(new Date())
            .build();
  }

  public Book get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<Book> getList() {

    final List<Book> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
