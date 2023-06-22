package com.asset.resource_server.controller;

import com.asset.resource_server.entity.Processor;
import com.asset.resource_server.service.ProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/processor")
@RequiredArgsConstructor
public class ProcessorController {

    private final ProcessorService processorService;

    @PostMapping
    public void save(String processor) {
        this.processorService.save(processor);
    }

    @PutMapping(path = "{id}")
    public void modify(@PathVariable(name = "id") Integer id, String newProcessor) {
        this.processorService.modify(id, newProcessor);
    }

    @GetMapping
    public ResponseEntity<List<Processor>> getAll() {
        return ResponseEntity.ok(this.processorService.fetchAll());
    }

    @DeleteMapping
    public void remove(Integer id) {
        this.processorService.deleteById(id);
    }
}
