package com.okuma.dostu.backend.dataAccess.abstracts;

import com.okuma.dostu.backend.entities.concretes.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String categoryName);
    Category findByName(String name);
}
