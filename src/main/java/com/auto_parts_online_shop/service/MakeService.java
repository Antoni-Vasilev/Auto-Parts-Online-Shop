package com.auto_parts_online_shop.service;

import com.auto_parts_online_shop.model.Make;

import java.util.List;

public interface MakeService {

    Make create(Make make);

    List<Make> listAll();

    Make getById(Long id);

    Make update(Long id, Make make);

    void delete(Long id);
}
