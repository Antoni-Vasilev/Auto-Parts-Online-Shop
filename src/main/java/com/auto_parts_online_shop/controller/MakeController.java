package com.auto_parts_online_shop.controller;

import com.auto_parts_online_shop.dto.Message;
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
    public ResponseEntity<Make> getManufacturer(@PathVariable Long id) {
        return ResponseEntity.ok(makeService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Make> updateManufacturer(@PathVariable Long id, @RequestBody @Valid Make make) {
        return ResponseEntity.ok(makeService.update(id, make));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Message>> delete(@PathVariable Long id) {
        makeService.delete(id);

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("message", String.format("Manufacturer with id (%s) was successfully deleted", id)));

        return ResponseEntity.ok(messages);
    }
}
