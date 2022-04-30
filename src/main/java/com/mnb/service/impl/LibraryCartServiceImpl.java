package com.mnb.service.impl;


import com.mnb.entity.Book;
import com.mnb.entity.LibraryCart;
import com.mnb.entity.User;
import com.mnb.entity.enumerations.LibraryCartStatus;
import com.mnb.entity.exception.*;
import com.mnb.repository.BookRepository;
import com.mnb.repository.LibraryCartRepository;
import com.mnb.repository.UserRepository;
import com.mnb.service.BookService;
import com.mnb.service.LibraryCartService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryCartServiceImpl implements LibraryCartService {

    private final LibraryCartRepository libraryCartRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

    public LibraryCartServiceImpl(LibraryCartRepository libraryCartRepository,
                                  UserRepository userRepository,
                                  BookService bookService) {
        this.libraryCartRepository = libraryCartRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    @Override
    public List<Book> listAllBooksInLibraryCart(Long cartId) {
        if(this.libraryCartRepository.findById(cartId).isPresent())
            return this.libraryCartRepository.findById(cartId).get().getBookList();
        else
        throw new LibraryCartNotFoundException(cartId);
    }

    @Override
    public LibraryCart getActiveLibraryCart(String username) {

        return this.libraryCartRepository
                .findByUserAndStatus(userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username)),
                        LibraryCartStatus.CREATED)
                .orElseGet(() -> {
                    User user = this.userRepository.findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username));
                    LibraryCart librarycart = new LibraryCart(user);
                    return this.libraryCartRepository.save(librarycart);
                });


    }

    @Override
    public LibraryCart addBookToLibraryCart(String username, Long bookId) {
        LibraryCart libraryCart = this.getActiveLibraryCart(username);
        Book book = this.bookService.findById(bookId);

        if(libraryCart.getBookList()
                .stream().filter(i -> i.getId().equals(bookId))
                .collect(Collectors.toList()).size() > 0)
            throw new BookAlreadyInLibraryCartException(bookId, username);
        libraryCart.getBookList().add(book);
        return this.libraryCartRepository.save(libraryCart);

    }

    @Override
    public LibraryCart findById(Long id) {
        return this.libraryCartRepository.findById(id).orElseThrow(() -> new InvalidLibraryCartException());
    }

    @Override
    public LibraryCart pay(Long id) {

        LibraryCart libraryCart= this.libraryCartRepository.findById(id).orElseThrow(() -> new InvalidLibraryCartException());
        libraryCart.getBookList().clear();
        this.libraryCartRepository.save(libraryCart);

        return libraryCart;
    }


}
