package com.auto_parts_online_shop.service;

import com.auto_parts_online_shop.model.PartCategory;

import java.util.List;

public interface PartCategoryService {

    PartCategory create(PartCategory partCategory);

    PartCategory get(Long id);

    List<PartCategory> getAll();

    PartCategory update(Long id, PartCategory partCategory);

    void delete(Long id);
}
