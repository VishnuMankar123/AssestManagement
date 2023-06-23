package com.asset.resource_server.embeddable;

import com.asset.resource_server.entity.DriveType;
import com.asset.resource_server.entity.MemoryType;
import com.asset.resource_server.entity.Processor;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Components {
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "processor_id", referencedColumnName = "id")
    private Processor processor;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "drive_type_id", referencedColumnName = "id")
    private DriveType driveType;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "memory_type_id", referencedColumnName = "id")
    private MemoryType memoryType;
}
