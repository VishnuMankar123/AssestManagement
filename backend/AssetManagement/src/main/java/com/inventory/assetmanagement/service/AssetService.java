package com.inventory.assetmanagement.service;

import com.inventory.assetmanagement.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
}
