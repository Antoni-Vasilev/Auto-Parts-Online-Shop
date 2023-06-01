package com.auto_parts_online_shop.service.impl;

import com.auto_parts_online_shop.exception.NotFoundException.NotFoundException;
import com.auto_parts_online_shop.model.PartCategory;
import com.auto_parts_online_shop.repository.PartCategoryRepository;
import com.auto_parts_online_shop.service.PartCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartCategoryServiceImpl implements PartCategoryService {

    private final PartCategoryRepository partCategoryRepository;

    @Override
    public PartCategory create(PartCategory partCategory) {
        return partCategoryRepository.save(partCategory);
    }

    @Override
    public PartCategory get(Long id) {
        PartCategory findPartCategory = partCategoryRepository.findPartCategoriesById(id);

        if (findPartCategory == null)
            throw new NotFoundException(String.format("A part category with this id (%s) does not exist", id));

        return findPartCategory;
    }

    @Override
    public List<PartCategory> getAll() {
        return partCategoryRepository.findAll();
    }

    @Override
    public PartCategory update(Long id, PartCategory partCategory) {
        get(id);

        PartCategory pc = PartCategory.builder()
                .id(id)
                .name(partCategory.getName())
                .description(partCategory.getDescription())
                .build();

        return partCategoryRepository.save(pc);
    }

    @Override
    public void delete(Long id) {
        PartCategory findPartCategory = get(id);
        partCategoryRepository.delete(findPartCategory);
    }
}
