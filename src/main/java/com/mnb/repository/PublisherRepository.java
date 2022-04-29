package com.mnb.repository;

import com.mnb.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {
    @Query("Select p from Publisher p where p.publisherName LIKE %?1%")
    List<Publisher> findPublisherByPublisherName(String keyword);
}
