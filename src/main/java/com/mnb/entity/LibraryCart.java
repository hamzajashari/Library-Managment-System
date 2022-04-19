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
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @Column(name = "USER")
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

    public LibraryCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.bookList = new ArrayList<>();
        this.status = LibraryCartStatus.CREATED;

    }
}
