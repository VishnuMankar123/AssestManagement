package com.asset.resource_server.controller;

import com.asset.resource_server.entity.Processor;
import com.asset.resource_server.service.ProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/processor")
@RequiredArgsConstructor
public class ProcessorController {

    private final ProcessorService processorService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('admin')")
    public void save(@RequestBody String processor) {
        this.processorService.save(processor);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize(value = "hasAuthority('admin')")
    public void modify(@PathVariable(name = "id") Integer id, @RequestBody String newProcessor) {
        this.processorService.modify(id, newProcessor);
    }

    @GetMapping
    public ResponseEntity<List<Processor>> getAll() {
        return ResponseEntity.ok(this.processorService.fetchAll());
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize(value = "hasAuthority('admin')")
    public void remove(@PathVariable(name = "id") Integer id) {
        this.processorService.deleteById(id);
    }
}
