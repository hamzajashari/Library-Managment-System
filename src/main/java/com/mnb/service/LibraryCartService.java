package com.mnb.service;

import com.mnb.entity.Book;
import com.mnb.entity.LibraryCart;

import java.util.List;
import java.util.Optional;

public interface LibraryCartService {
    List<Book> listAllBooksInLibraryCart(Long cartId);
    LibraryCart getActiveLibraryCart(String username);
    LibraryCart addBookToLibraryCart(String username, Long bookId);
    LibraryCart findById(Long id);
    LibraryCart pay(Long id);
    int charge(Long id);

}
