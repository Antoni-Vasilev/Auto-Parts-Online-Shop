package com.auto_parts_online_shop.controller;

import com.auto_parts_online_shop.dto.Message;
import com.auto_parts_online_shop.dto.PartDto;
import com.auto_parts_online_shop.exception.FormatException.FormatException;
import com.auto_parts_online_shop.model.Part;
import com.auto_parts_online_shop.service.PartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/parts")
@RequiredArgsConstructor
public class PartController {

    private final PartService partService;

    @GetMapping
    public ResponseEntity<List<Part>> getAll() {
        return ResponseEntity.ok(partService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Part> get(@PathVariable String id) {
        long lId;
        try {
            lId = Long.parseLong(id);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        return ResponseEntity.ok(partService.get(lId));
    }

    @GetMapping("/{categoryId}/{modelId}")
    public ResponseEntity<List<Part>> getAllByCategoryIdAndModelId(@PathVariable Long categoryId, @PathVariable Long modelId) {
        return ResponseEntity.ok(partService.getAllByCategoryIdAndModelId(categoryId, modelId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Part>> getAllBySearch(@RequestParam("name") String name) {
        return ResponseEntity.ok(partService.getAllByName(name));
    }

    @PostMapping
    public ResponseEntity<Part> create(@RequestBody @Valid PartDto partDto) {
        return ResponseEntity.ok(partService.create(partDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Part> update(@RequestBody @Valid PartDto partDto, @PathVariable Long id) {
        return ResponseEntity.ok(partService.update(id, partDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Message>> delete(@PathVariable String id) {
        long lId;
        try {
            lId = Long.parseLong(id);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        partService.delete(lId);

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("message", "Part with id (%s) was successfully deleted"));

        return ResponseEntity.ok(messages);
    }
}
