package com.asset.resource_server.repository;

import com.asset.resource_server.embeddable.Components;
import com.asset.resource_server.entity.Asset;
import com.asset.resource_server.entity.AssetType;
import com.asset.resource_server.entity.Department;
import com.asset.resource_server.entity.DriveType;
import com.asset.resource_server.entity.Location;
import com.asset.resource_server.entity.Manufacturer;
import com.asset.resource_server.entity.MemoryType;
import com.asset.resource_server.entity.Model;
import com.asset.resource_server.entity.OperatingSystem;
import com.asset.resource_server.entity.Processor;
import com.asset.resource_server.enumeration.AssetAllocated;
import com.asset.resource_server.enumeration.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.asset.resource_server.utils.RandomValueGenerator.generateAlphaNumericString;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AssetRepositoryTest {
    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private AssetTypeRepository assetTypeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DriveTypeRepository driveTypeRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private MemoryTypeRepository memoryTypeRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private OperatingSystemRepository operatingSystemRepository;

    @Autowired
    private ProcessorRepository processorRepository;


    private final String assetSerialNumber = generateAlphaNumericString(20);

    @BeforeEach
    void setup() {
        AssetType assetType = AssetType.builder().type(generateAlphaNumericString(5)).build();
        Department department = Department.builder().departmentName(generateAlphaNumericString(10)).build();
        DriveType driveType = DriveType.builder().secondaryMemory(generateAlphaNumericString(8)).build();
        Location location = Location.builder().city(generateAlphaNumericString(9)).build();
        Manufacturer manufacturer = Manufacturer.builder().manufacturerName(generateAlphaNumericString(11)).build();
        MemoryType memoryType = MemoryType.builder().primaryMemory(generateAlphaNumericString(12)).build();
        Model model = Model.builder().modelName(generateAlphaNumericString(13)).build();
        OperatingSystem operatingSystem = OperatingSystem.builder().operatingSystemName(generateAlphaNumericString(14)).build();
        Processor processor = Processor.builder().processorName(generateAlphaNumericString(10)).build();

        Asset asset = Asset.builder()
                .name(generateAlphaNumericString(6))
                .employeeId(generateAlphaNumericString(15))
                .department(this.departmentRepository.save(department))
                .assetAllocated(AssetAllocated.YES)
                .assetType(this.assetTypeRepository.save(assetType))
                .assignDate(LocalDate.now())
                .manufacturer(this.manufacturerRepository.save(manufacturer))
                .serialNumber(this.assetSerialNumber)
                .model(this.modelRepository.save(model))
                .operatingSystem(this.operatingSystemRepository.save(operatingSystem))
                .location(this.locationRepository.save(location))
                .components(Components.builder()
                        .processor(this.processorRepository.save(processor))
                        .driveType(this.driveTypeRepository.save(driveType))
                        .memoryType(this.memoryTypeRepository.save(memoryType))
                        .build())
                .warrantyStartDate(LocalDate.now().minusYears(1L))
                .warrantyExpiryDate(LocalDate.now().plusYears(1L))
                .status(Status.LIVE)
                .previousUserAssetReturnDate(null)
                .build();

        this.assetRepository.save(asset);
    }

    @DisplayName(value = """
            When the correct serial number is given,
            The asset should be retrieved successfully.
            """)
    @Test
    void whenCorrectSerialNumberIsGivenFetchAssetBySerialNumberWorks() {
        Optional<Asset> fetchedAsset = this.assetRepository.findBySerialNumber(this.assetSerialNumber);
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
