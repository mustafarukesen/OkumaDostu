package com.okuma.dostu.backend.dataAccess.abstracts;

import com.okuma.dostu.backend.entities.concretes.Publisher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    boolean existsByName(String publisherName);
    Publisher findByName(String name);
}
