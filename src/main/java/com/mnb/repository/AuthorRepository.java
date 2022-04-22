package com.mnb.repository;

import com.mnb.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("Select a from Author a where a.authorName LIKE %?1%"
            + "OR a.authorSurname LIKE %?1%")
    List<Author> findAuthorByAuthorName(String name);
    //no need to any code
}
