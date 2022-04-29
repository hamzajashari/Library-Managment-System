package com.mnb.entity;


import com.mnb.entity.enumerations.LibraryCartStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LIBRARY_CART")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class LibraryCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    @Column(name = "BOOK_LIST")
        private List<Book> bookList;

    @Column(name ="DATE_CREATED")
    private LocalDateTime dateCreated;

    @Enumerated(EnumType.STRING)
    private LibraryCartStatus status;

    public LibraryCart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LibraryCartStatus getStatus() {
        return status;
    }

    public void setStatus(LibraryCartStatus status) {
        this.status = status;
    }

    public LibraryCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.bookList = new ArrayList<>();
        this.status = LibraryCartStatus.CREATED;

    }
}
