package com.auto_parts_online_shop.controller;

import com.auto_parts_online_shop.dto.Message;
import com.auto_parts_online_shop.exception.FormatException.FormatException;
import com.auto_parts_online_shop.model.Make;
import com.auto_parts_online_shop.service.MakeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/makes")
@RequiredArgsConstructor
public class MakeController {

    private final MakeService makeService;

    @PostMapping
    public ResponseEntity<Make> createManufacturer(@RequestBody @Valid Make make) {
        return ResponseEntity.ok(makeService.create(make));
    }

    @GetMapping
    public ResponseEntity<List<Make>> listManufacturer() {
        return ResponseEntity.ok(makeService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Make> getManufacturer(@PathVariable String id) {
        long lId;
        try {
            lId = Long.parseLong(id);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        return ResponseEntity.ok(makeService.getById(lId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Make> updateManufacturer(@PathVariable String id, @RequestBody @Valid Make make) {
        long lId;
        try {
            lId = Long.parseLong(id);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        return ResponseEntity.ok(makeService.update(lId, make));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Message>> delete(@PathVariable String id) {
        long lId;
        try {
            lId = Long.parseLong(id);
        } catch (Exception e) {
            throw new FormatException(e.getMessage());
        }

        makeService.delete(lId);

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("message", String.format("Manufacturer with id (%s) was successfully deleted", lId)));

        return ResponseEntity.ok(messages);
    }
}
