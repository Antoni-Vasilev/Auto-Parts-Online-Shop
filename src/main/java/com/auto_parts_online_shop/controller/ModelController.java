package com.auto_parts_online_shop.controller;

import com.auto_parts_online_shop.dto.Message;
import com.auto_parts_online_shop.dto.ModelDto;
import com.auto_parts_online_shop.model.Make;
import com.auto_parts_online_shop.model.Model;
import com.auto_parts_online_shop.service.MakeService;
import com.auto_parts_online_shop.service.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;
    private final MakeService makeService;

    @PostMapping
    private ResponseEntity<Model> create(@RequestBody @Valid ModelDto model) {
        Model m = Model.builder()
                .name(model.getName())
                .make(makeService.getById(model.getMakeId()))
                .build();
        return ResponseEntity.ok(modelService.create(m));
    }

    @GetMapping("/{id}")
    private ResponseEntity<Model> get(@PathVariable Long id) {
        return ResponseEntity.ok(modelService.get(id));
    }

    @GetMapping("/makes/{makeId}")
    private ResponseEntity<List<Model>> getAllByMake(@PathVariable Long makeId) {
        Make findMake = makeService.getById(makeId);
        return ResponseEntity.ok(modelService.getAllByMake(findMake));
    }

    @PutMapping("/{id}")
    private ResponseEntity<Model> update(@PathVariable Long id, @RequestBody @Valid ModelDto model) {
        Model m = Model.builder()
                .id(model.getId())
                .name(model.getName())
                .make(makeService.getById(model.getMakeId()))
                .build();
        return ResponseEntity.ok(modelService.update(id, m));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<List<Message>> delete(@PathVariable Long id) {
        modelService.delete(id);

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("message", "Model with id (%s) was successfully deleted"));

        return ResponseEntity.ok(messages);
    }
}