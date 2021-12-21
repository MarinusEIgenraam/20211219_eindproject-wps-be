package com.willpowered.eindprojectwpsbe.repository;

import com.willpowered.eindprojectwpsbe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
