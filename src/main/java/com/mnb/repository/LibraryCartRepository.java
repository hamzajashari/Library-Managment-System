package com.mnb.repository;


import com.mnb.entity.LibraryCart;
import com.mnb.entity.User;
import com.mnb.entity.enumerations.LibraryCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryCartRepository extends JpaRepository<LibraryCart, Long> {
        Optional<LibraryCart> findByUserAndStatus(User user, LibraryCartStatus status);
}
