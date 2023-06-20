package com.asset.resource_server.service;

import com.asset.resource_server.entity.Model;
import com.asset.resource_server.exception.ResourceNotFoundException;
import com.asset.resource_server.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;

    @Transactional(isolation = SERIALIZABLE)
    public void save(String newModel) {
        this.modelRepository.save(Model.builder()
                .modelName(newModel)
                .build());
    }

    @Transactional(isolation = SERIALIZABLE)
    public void modify(Integer id, String newModel) {
        Model previous = this.modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model", "id", id));

        previous.setModelName(newModel);

        this.modelRepository.save(previous);
    }

    public List<String> fetchAll() {
        return this.modelRepository.findAll()
                .stream()
                .map(Model::getModelName)
                .toList();
    }

    public Model fetchById(Integer id) {
        return this.modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model", "id", id));
    }

    public void deleteById(Integer id) {
        this.modelRepository.deleteById(id);
    }
}
