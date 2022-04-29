package com.mnb.service;

import com.mnb.entity.Book;
import com.mnb.entity.LibraryCart;

import java.util.List;

public interface LibraryCartService {
    List<Book> listAllBooksInLibraryCart(Long cartId);
    LibraryCart getActiveLibraryCart(String username);
    LibraryCart addBookToLibraryCart(String username, Long bookId);

}
