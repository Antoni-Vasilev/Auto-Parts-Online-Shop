package com.auto_parts_online_shop.repository;

import com.auto_parts_online_shop.model.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeRepository extends JpaRepository<Make, Long> {

    Make findMakeById(Long id);
}
