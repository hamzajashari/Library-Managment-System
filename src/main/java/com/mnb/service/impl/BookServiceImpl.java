package com.mnb.service.impl;

import com.mnb.exception.NotFoundException;
import com.mnb.repository.BookRepository;
import com.mnb.entity.Book;
import com.mnb.service.BookService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookServiceImpl implements BookService {
    final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(long theId) {
        return bookRepository.findById(theId)
                .orElseThrow(() -> new NotFoundException(String.format(" not found  with ID %d", theId)));
    }

    @Override
    public void save(Book theBook) {
        bookRepository.save(theBook);
    }

    @Override
    public void deleteById(long theId) {
        bookRepository.deleteById(theId);
    }

    @Override
    public List<Book> findBookByBookName(String keyword) {
        return bookRepository.findBookByBookName(keyword);
    }
}
