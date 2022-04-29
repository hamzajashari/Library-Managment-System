package com.mnb.service.impl;

import com.mnb.entity.exception.NotFoundException;
import com.mnb.repository.PublisherRepository;
import com.mnb.entity.Publisher;
import com.mnb.service.PublisherService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublisherServiceImpl implements PublisherService {
    final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public List<Publisher> findByPublisherName(String keyword) {
        return publisherRepository.findPublisherByPublisherName(keyword);
    }

    @Override
    public Publisher findById(long theId) {
       /* Optional<Publisher> result = publisherRepository.findById(theId);

        Publisher thePublisher = null;

        if (result.isPresent()) {
            thePublisher = result.get();
        }
        else {
            // we didn't find the publisher
            throw new RuntimeException("Did not find publisher id - " + theId);
        }

        return thePublisher;

 */
         return publisherRepository.findById(theId)
                .orElseThrow(() -> new NotFoundException(String.format("Publisher not found  with ID %d", theId)));
    }


    @Override
    public void save(Publisher thePublisher) {
        publisherRepository.save(thePublisher);
    }

    @Override
    public void deleteById(long theId) {
        publisherRepository.deleteById(theId);
    }
}
