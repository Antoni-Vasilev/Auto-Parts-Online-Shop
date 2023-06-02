package com.auto_parts_online_shop.service.impl;

import com.auto_parts_online_shop.dto.PartDto;
import com.auto_parts_online_shop.exception.NotFoundException.NotFoundException;
import com.auto_parts_online_shop.model.Model;
import com.auto_parts_online_shop.model.Part;
import com.auto_parts_online_shop.model.PartCategory;
import com.auto_parts_online_shop.repository.PartRepository;
import com.auto_parts_online_shop.service.ModelService;
import com.auto_parts_online_shop.service.PartCategoryService;
import com.auto_parts_online_shop.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final PartCategoryService partCategoryService;
    private final ModelService modelService;

    @Override
    public List<Part> getAll() {
        return partRepository.findAll();
    }

    @Override
    public Part get(Long id) {
        Part findPart = partRepository.findPartById(id);

        if (findPart == null)
            throw new NotFoundException(String.format("A part with this id (%s) does not exist", id));

        return findPart;
    }

    @Override
    public List<Part> getAllByCategoryIdAndModelId(Long categoryId, Long modelId) {
        List<Part> findParts = partRepository.findAll();
        findParts = findParts.stream()
                .filter(it -> it.getPartCategory().getId() == categoryId)
                .filter(it -> it.getModelList().stream().anyMatch(itt -> itt.getId() == modelId))
                .toList();

//        for (int i = 0; i < findParts.size(); i++) {
//            Part part = findParts.get(i);
//
//            if (!Objects.equals(part.getPartCategory().getId(), categoryId)) findParts.remove(part);
//
//            boolean isFinish = false;
//            for (Model model : part.getModelList()) {
//                if (Objects.equals(model.getId(), modelId)) {
//                    isFinish = true;
//                    break;
//                }
//            }
//
//            if (!isFinish) findParts.remove(part);
//        }

        return findParts;
    }

    @Override
    public List<Part> getAllByName(String name) {
        return partRepository.findAllByName(name);
    }

    @Override
    public Part create(PartDto partDto) {
        PartCategory partCategory = partCategoryService.get(partDto.getCategoryId());
        List<Model> modelList = new ArrayList<>();
        for (Long id : partDto.getModelIds()) modelList.add(modelService.get(id));

        return partRepository.save(Part.builder()
                .name(partDto.getName())
                .description(partDto.getDescription())
                .price(partDto.getPrice())
                .partCategory(partCategory)
                .modelList(modelList)
                .build());
    }

    @Override
    public Part update(Long id, PartDto partDto) {
        get(id);

        PartCategory partCategory = partCategoryService.get(partDto.getCategoryId());
        List<Model> modelList = new ArrayList<>();
        for (Long ids : partDto.getModelIds()) modelList.add(modelService.get(ids));

        return partRepository.save(Part.builder()
                .id(id)
                .name(partDto.getName())
                .description(partDto.getDescription())
                .price(partDto.getPrice())
                .partCategory(partCategory)
                .modelList(modelList)
                .build());
    }

    @Override
    public void delete(Long id) {
        Part findPart = get(id);
        partRepository.delete(findPart);
    }
}
