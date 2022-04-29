package com.mnb.service;

import com.mnb.entity.Author;
import com.mnb.entity.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<Author> findAll();

    public Author findById(long theId);

    public void save(Author theAuthor);

    public void deleteById(long theId);

    public void addBook(Author author, Book book);

    Optional<Author> getAuthor(long authorId);

    List<Author> findByAuthorName(String name);
}
