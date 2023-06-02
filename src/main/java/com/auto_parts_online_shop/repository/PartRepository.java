package com.auto_parts_online_shop.repository;

import com.auto_parts_online_shop.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    Part findPartById(Long id);

    List<Part> findAllByPartCategory_Id(Long categoryId);

    List<Part> findAllByName(String name);
}
