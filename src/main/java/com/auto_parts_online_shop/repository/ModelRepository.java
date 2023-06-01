package com.auto_parts_online_shop.repository;

import com.auto_parts_online_shop.model.Make;
import com.auto_parts_online_shop.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Model findModelById(Long id);

    List<Model> getAllByMake(Make make);
}
