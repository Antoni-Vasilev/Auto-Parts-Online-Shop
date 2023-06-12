package com.auto_parts_online_shop.controller;

import com.auto_parts_online_shop.dto.Message;
import com.auto_parts_online_shop.exception.FormatException.FormatException;
import com.auto_parts_online_shop.model.PartCategory;
import com.auto_parts_online_shop.service.PartCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/part-category")
@RequiredArgsConstructor
public class PartCategoryController {

    private final PartCategoryService partCategoryService;

    @PostMapping
    public ResponseEntity<PartCategory> create(@RequestBody @Valid PartCategory partCategory) {
        return ResponseEntity.ok(partCategoryService.create(partCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartCategory> get(@PathVariable String id) {
        long lId;
        try {
            lId = Long.parseLong(id);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        return ResponseEntity.ok(partCategoryService.get(lId));
    }

    @GetMapping
    public ResponseEntity<List<PartCategory>> getAll() {
        return ResponseEntity.ok(partCategoryService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartCategory> update(@PathVariable String id, @RequestBody PartCategory partCategory) {
        long lId;
        try {
            lId = Long.parseLong(id);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        return ResponseEntity.ok(partCategoryService.update(lId, partCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Message>> delete(@PathVariable String id) {
        long lId;
        try {
            lId = Long.parseLong(id);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        partCategoryService.delete(lId);

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("message", "Part category with id (%s) was successfully deleted"));

        return ResponseEntity.ok(messages);
    }
}
