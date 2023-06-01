package com.auto_parts_online_shop.repository;

import com.auto_parts_online_shop.model.PartCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartCategoryRepository extends JpaRepository<PartCategory, Long> {

    PartCategory findPartCategoriesById(Long id);
}
