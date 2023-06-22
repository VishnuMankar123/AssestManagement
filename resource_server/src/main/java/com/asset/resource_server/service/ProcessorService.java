package com.asset.resource_server.service;

import com.asset.resource_server.entity.Processor;
import com.asset.resource_server.exception.ResourceNotFoundException;
import com.asset.resource_server.repository.ProcessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProcessorService {

    private final ProcessorRepository processorRepository;

    @Transactional(isolation = SERIALIZABLE)
    public void save(String newProcessor) {
        this.processorRepository.save(Processor.builder()
                .processorName(newProcessor)
                .build());
    }

    @Transactional(isolation = SERIALIZABLE)
    public void modify(Integer id, String newProcessor) {
        Processor previous = this.processorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Processor", "id", id));

        previous.setProcessorName(newProcessor);

        this.processorRepository.save(previous);
    }

    public List<Processor> fetchAll() {
        return this.processorRepository.findAll()
                .stream()
                .toList();
    }

    public Processor fetchById(Integer id) {
        return this.processorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Processor", "id", id));
    }

    public void deleteById(Integer id) {
        this.processorRepository.deleteById(id);
    }
}
