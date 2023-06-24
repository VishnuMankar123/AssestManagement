package com.asset.resource_server.repository;

import com.asset.resource_server.entity.Asset;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.asset.resource_server.utils.RandomValueGenerator.generateAlphaNumericString;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AssetRepositoryTest {

    @Autowired
    private AssetRepository assetRepository;

    @DisplayName(value = """
            When the correct serial number is given,
            The asset should be retrieved successfully.
            """)
    @Test
    void whenCorrectSerialNumberIsGivenFetchAssetBySerialNumberWorks() {
        String assetSerialNumber = "serial";
        Optional<Asset> fetchedAsset = this.assetRepository.findBySerialNumber(assetSerialNumber);
        Assertions.assertThat(fetchedAsset).isPresent();
    }

    @DisplayName(value = """
            When an incorrect serial number is given,
            The retrieved optional asset should not be present.
            """)
    @Test
    void whenIncorrectSerialNumberIsGivenFetAssetBySerialNumberFails() {
        String wrongSerialNumber = generateAlphaNumericString(20);
        Optional<Asset> fetchedAsset = this.assetRepository.findBySerialNumber(wrongSerialNumber);
        Assertions.assertThat(fetchedAsset).isNotPresent();
    }
}
