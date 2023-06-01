package com.auto_parts_online_shop.service;

import com.auto_parts_online_shop.model.Make;
import com.auto_parts_online_shop.model.Model;

import java.util.List;

public interface ModelService {

    Model create(Model model);

    Model get(Long id);

    List<Model> getAllByMake(Make make);

    Model update(Long id, Model model);

    void delete(Long id);
}
