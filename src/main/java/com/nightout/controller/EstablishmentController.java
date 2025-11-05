package com.nightout.controller;

import com.nightout.model.Establishment;
import com.nightout.service.EstablishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/establishments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EstablishmentController {

    private final EstablishmentService service;

    @GetMapping
    public ResponseEntity<List<Establishment>> list() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Establishment> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Establishment> create(@RequestBody Establishment e) {
        return ResponseEntity.ok(service.save(e));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Establishment> update(@PathVariable Long id, @RequestBody Establishment e) {
        return ResponseEntity.ok(service.update(id, e));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
