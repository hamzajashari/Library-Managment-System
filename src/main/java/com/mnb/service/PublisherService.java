package com.mnb.service;

import com.mnb.entity.Author;
import com.mnb.entity.Publisher;

import java.util.List;

public interface PublisherService {
    public List<Publisher> findAll();

    public Publisher findById(long theId);

    public void save(Publisher thePublisher);

    public void deleteById(long theId);
}
