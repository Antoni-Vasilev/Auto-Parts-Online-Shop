package com.auto_parts_online_shop.service.impl;

import com.auto_parts_online_shop.exception.NotFoundException.NotFoundException;
import com.auto_parts_online_shop.model.Make;
import com.auto_parts_online_shop.repository.MakeRepository;
import com.auto_parts_online_shop.service.MakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MakeServiceImpl implements MakeService {

    private final MakeRepository makeRepository;

    @Override
    public Make create(Make make) {
        return makeRepository.save(make);
    }

    @Override
    public List<Make> listAll() {
        return makeRepository.findAll();
    }

    @Override
    public Make getById(Long id) {
        Make findMake = makeRepository.findMakeById(id);

        if (findMake == null)
            throw new NotFoundException(String.format("A manufacturer with this id (%s) does not exist", id));

        return findMake;
    }

    @Override
    public Make update(Long id, Make make) {
        getById(id);

        Make newMake = Make.builder()
                .id(id)
                .name(make.getName())
                .build();
        return makeRepository.save(newMake);
    }

    @Override
    public void delete(Long id) {
        Make findMake = getById(id);

        makeRepository.delete(findMake);
    }
}
