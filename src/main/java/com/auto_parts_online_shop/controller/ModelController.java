package com.auto_parts_online_shop.controller;

import com.auto_parts_online_shop.dto.Message;
import com.auto_parts_online_shop.dto.ModelDto;
import com.auto_parts_online_shop.exception.FormatException.FormatException;
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
    public ResponseEntity<Model> create(@RequestBody @Valid ModelDto model) {
        Model m = Model.builder()
                .name(model.getName())
                .make(makeService.getById(model.getMakeId()))
                .build();
        return ResponseEntity.ok(modelService.create(m));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> get(@PathVariable String id) {
        long lId;
        try {
            lId = Long.parseLong(id);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        return ResponseEntity.ok(modelService.get(lId));
    }

    @GetMapping("/makes/{makeId}")
    public ResponseEntity<List<Model>> getAllByMake(@PathVariable String makeId) {
        long lId;
        try {
            lId = Long.parseLong(makeId);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        Make findMake = makeService.getById(lId);
        return ResponseEntity.ok(modelService.getAllByMake(findMake));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> update(@PathVariable String id, @RequestBody @Valid ModelDto model) {
        long lId;
        try {
            lId = Long.parseLong(id);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        Model m = Model.builder()
                .id(model.getId())
                .name(model.getName())
                .make(makeService.getById(model.getMakeId()))
                .build();
        return ResponseEntity.ok(modelService.update(lId, m));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Message>> delete(@PathVariable String id) {
        long lId;
        try {
            lId = Long.parseLong(id);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        modelService.delete(lId);

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("message", String.format("Model with id (%s) was successfully deleted", id)));

        return ResponseEntity.ok(messages);
    }
}