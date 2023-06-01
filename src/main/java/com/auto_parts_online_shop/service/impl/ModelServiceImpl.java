package com.auto_parts_online_shop.service.impl;

import com.auto_parts_online_shop.exception.NotFoundException.NotFoundException;
import com.auto_parts_online_shop.model.Make;
import com.auto_parts_online_shop.model.Model;
import com.auto_parts_online_shop.repository.ModelRepository;
import com.auto_parts_online_shop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    @Override
    public Model create(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public Model get(Long id) {
        Model model = modelRepository.findModelById(id);
        if (model == null) throw new NotFoundException(String.format("A model with id (%s) does not exist", id));
        return model;
    }

    @Override
    public List<Model> getAllByMake(Make make) {
        return modelRepository.getAllByMake(make);
    }

    @Override
    public Model update(Long id, Model model) {
        get(id);
        Model newModel = Model.builder()
                .id(id)
                .name(model.getName())
                .make(model.getMake())
                .build();
        return modelRepository.save(newModel);
    }

    @Override
    public void delete(Long id) {
        Model findModel = get(id);
        modelRepository.delete(findModel);
    }
}
