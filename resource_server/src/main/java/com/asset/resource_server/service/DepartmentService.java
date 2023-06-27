package com.asset.resource_server.service;

import com.asset.resource_server.entity.Department;
import com.asset.resource_server.exception.ResourceNotFoundException;
import com.asset.resource_server.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional(isolation = SERIALIZABLE)
    public void save(String department) {
        this.departmentRepository.save(Department.builder()
                .departmentName(department)
                .build());
    }

    @Transactional(isolation = SERIALIZABLE)
    public void modify(Integer id, String newDepartmentName) {
        Department previous = this.departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        previous.setDepartmentName(newDepartmentName);

        this.departmentRepository.save(previous);
    }

    public List<Department> fetchAll() {
        return this.departmentRepository.findAll()
                .stream()
                .toList();
    }

    public Department findById(Integer id) {
        return this.departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }

    @Transactional
    public void deleteById(Integer id) {
        this.departmentRepository.deleteById(id);
    }
}
