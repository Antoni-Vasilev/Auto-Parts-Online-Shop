package com.auto_parts_online_shop.service;

import com.auto_parts_online_shop.dto.PartDto;
import com.auto_parts_online_shop.model.Part;

import java.util.List;

public interface PartService {

    List<Part> getAll();

    Part get(Long id);

    List<Part> getAllByCategoryIdAndModelId(Long categoryId, Long modelId);

    List<Part> getAllByName(String name);

    Part create(PartDto partDto);

    Part update(Long id, PartDto partDto);

    void delete(Long id);
}
