package com.mnb.service.impl;

import com.mnb.repository.AuthorRepository;
import com.mnb.entity.Author;
import com.mnb.entity.Book;
import com.mnb.service.AuthorService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorServiceImpl implements AuthorService {
    final AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(long theId) {
        Optional<Author> result = authorRepository.findById(theId);
        Author theAuthor = null;
        if (result.isPresent()) {
            theAuthor = result.get();
        }
        else {
            // we didn't find the book
            throw new RuntimeException("Did not find author id - " + theId);
        }
        return theAuthor;
    }

    @Override
    public void save(Author theAuthor) {
        authorRepository.save(theAuthor);
    }

    @Override
    public void deleteById(long theId) {
        authorRepository.deleteById(theId);
    }

    @Override
    public void addBook(Author author, Book book) {
        if( authorRepository.findById(author.getId()).isPresent())
            authorRepository.findById(author.getId()).get().getBooksList().add(book);
    }

    @Override
    public Optional<Author> getAuthor(long authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    public List<Author> findByAuthorName(String name) {
        return authorRepository.findAuthorByAuthorName(name);
    }

}
