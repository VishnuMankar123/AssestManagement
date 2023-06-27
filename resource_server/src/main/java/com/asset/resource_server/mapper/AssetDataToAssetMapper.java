package com.asset.resource_server.mapper;

import com.asset.resource_server.data.AssetData;
import com.asset.resource_server.embeddable.Components;
import com.asset.resource_server.entity.Asset;
import com.asset.resource_server.service.AssetService;
import com.asset.resource_server.service.AssetTypeService;
import com.asset.resource_server.service.DepartmentService;
import com.asset.resource_server.service.DriveTypeService;
import com.asset.resource_server.service.LocationService;
import com.asset.resource_server.service.ManufacturerService;
import com.asset.resource_server.service.MemoryTypeService;
import com.asset.resource_server.service.ModelService;
import com.asset.resource_server.service.OperatingSystemService;
import com.asset.resource_server.service.ProcessorService;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {AssetService.class}
)
public abstract class AssetDataToAssetMapper {

    @Autowired
    protected DepartmentService departmentService;
    @Autowired
    protected AssetTypeService assetTypeService;
    @Autowired
    protected ManufacturerService manufacturerService;
    @Autowired
    protected ModelService modelService;
    @Autowired
    protected OperatingSystemService operatingSystemService;
    @Autowired
    protected LocationService locationService;
    @Autowired
    protected ProcessorService processorService;
    @Autowired
    protected DriveTypeService driveTypeService;
    @Autowired
    protected MemoryTypeService memoryTypeService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "operatingSystem", expression = "java(this.operatingSystemService.fetchById(assetData.operatingSystemId()))")
    @Mapping(target = "model", expression = "java(this.modelService.fetchById(assetData.manufacturerId()))")
    @Mapping(target = "manufacturer", expression = "java(this.manufacturerService.findById(assetData.manufacturerId()))")
    @Mapping(target = "location", expression = "java(this.locationService.findById(assetData.locationId()))")
    @Mapping(target = "assetType", expression = "java(this.assetTypeService.findById(assetData.assetType()))")
    @Mapping(target = "department", expression = "java(this.departmentService.findById(assetData.departmentId()))")
    @Mapping(target = "components", expression = "java(getComponents(assetData.processorId(), assetData.driveTypeId(), assetData.memoryTypeId()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateAssetFromAssetData(AssetData assetData, @MappingTarget Asset asset);

    @Mapping(target = "assetAllocated", source = "assetAllocated")
    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "operatingSystem", expression = "java(this.operatingSystemService.fetchById(assetData.operatingSystemId()))")
    @Mapping(target = "model", expression = "java(this.modelService.fetchById(assetData.manufacturerId()))")
    @Mapping(target = "manufacturer", expression = "java(this.manufacturerService.findById(assetData.manufacturerId()))")
    @Mapping(target = "location", expression = "java(this.locationService.findById(assetData.locationId()))")
    @Mapping(target = "assetType", expression = "java(this.assetTypeService.findById(assetData.assetType()))")
    @Mapping(target = "department", expression = "java(this.departmentService.findById(assetData.departmentId()))")
    @Mapping(target = "components", expression = "java(getComponents(assetData.processorId(), assetData.driveTypeId(), assetData.memoryTypeId()))")
    public abstract Asset fromAssetData(AssetData assetData);

    @Mapping(target = "processorId", source = "asset.components.processor.id")
    @Mapping(target = "operatingSystemId", source = "asset.operatingSystem.id")
    @Mapping(target = "modelId", source = "asset.model.id")
    @Mapping(target = "memoryTypeId", source = "asset.components.memoryType.id")
    @Mapping(target = "manufacturerId", source = "asset.manufacturer.id")
    @Mapping(target = "locationId", source = "asset.location.id")
    @Mapping(target = "driveTypeId", source = "asset.components.driveType.id")
    @Mapping(target = "departmentId", source = "asset.department.id")
    @Mapping(target = "assetType", source = "asset.assetType.id")
    public abstract AssetData fromAsset(Asset asset);

    protected Components getComponents(Integer processorId, Integer driveTypeId, Integer memoryTypeId) {
        return Components.builder()
                .processor(this.processorService.fetchById(processorId))
                .driveType(this.driveTypeService.fetchById(driveTypeId))
                .memoryType(this.memoryTypeService.fetchById(memoryTypeId))
                .build();
    }
}
