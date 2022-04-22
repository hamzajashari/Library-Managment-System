package com.mnb.service;


import com.mnb.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(long theId);

    void save(Book theBook);

    void deleteById(long theId);

    List<Book> findBookByBookName(String keyword);
}
