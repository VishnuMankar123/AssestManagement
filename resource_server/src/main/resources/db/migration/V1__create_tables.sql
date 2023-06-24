CREATE TABLE asset_type
(
    id   INT AUTO_INCREMENT
        PRIMARY KEY,
    type VARCHAR(100) NOT NULL,
    CONSTRAINT unique_type
        UNIQUE (type)
);

CREATE TABLE department
(
    id              INT AUTO_INCREMENT
        PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL,
    CONSTRAINT unique_department_name
        UNIQUE (department_name)
);

CREATE TABLE drive_type
(
    id               INT AUTO_INCREMENT
        PRIMARY KEY,
    secondary_memory VARCHAR(100) NOT NULL,
    CONSTRAINT unique_secondary_memory
        UNIQUE (secondary_memory)
);

CREATE TABLE location
(
    id   INT AUTO_INCREMENT
        PRIMARY KEY,
    city VARCHAR(100) NULL,
    CONSTRAINT unique_city
        UNIQUE (city)
);

CREATE TABLE manufacturer
(
    id                INT AUTO_INCREMENT
        PRIMARY KEY,
    manufacturer_name VARCHAR(100) NOT NULL,
    CONSTRAINT unique_manufacturer_name
        UNIQUE (manufacturer_name)
);

CREATE TABLE memory_type
(
    id             INT AUTO_INCREMENT
        PRIMARY KEY,
    primary_memory VARCHAR(100) NOT NULL,
    CONSTRAINT unique_primary_memory
        UNIQUE (primary_memory)
);

CREATE TABLE model
(
    id         INT AUTO_INCREMENT
        PRIMARY KEY,
    model_name VARCHAR(100) NOT NULL,
    CONSTRAINT unique_model_name
        UNIQUE (model_name)
);

CREATE TABLE operating_system
(
    id                    INT AUTO_INCREMENT
        PRIMARY KEY,
    operating_system_name VARCHAR(100) NOT NULL,
    CONSTRAINT unique_operating_system_name
        UNIQUE (operating_system_name)
);

CREATE TABLE processor
(
    id             INT AUTO_INCREMENT
        PRIMARY KEY,
    processor_name VARCHAR(100) NOT NULL,
    CONSTRAINT unique_processor_name
        UNIQUE (processor_name)
);

CREATE TABLE asset
(
    id                              BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    name                            VARCHAR(200) NOT NULL,
    employee_id                     VARCHAR(100) NOT NULL,
    department_id                   INT          NOT NULL,
    asset_allocated                 TINYINT      NOT NULL,
    type_id                         INT          NOT NULL,
    assign_date                     DATE NULL,
    manufacturer_id                 INT          NOT NULL,
    serial_number                   VARCHAR(100) NOT NULL,
    model_id                        INT          NOT NULL,
    operating_system_id             INT          NOT NULL,
    location_id                     INT          NOT NULL,
    processor_id                    INT          NOT NULL,
    drive_type_id                   INT          NOT NULL,
    memory_type_id                  INT          NOT NULL,
    warranty_start_date             DATE NULL,
    warranty_expiry_date            DATE NULL,
    status                          TINYINT      NOT NULL,
    previous_user_asset_return_date DATE NULL,
    CONSTRAINT unique_name
        UNIQUE (name),
    CONSTRAINT unique_serial_number
        UNIQUE (serial_number),
    CONSTRAINT asset_asset_type_id_fk
        FOREIGN KEY (type_id) REFERENCES asset_type (id),
    CONSTRAINT asset_department_id_fk
        FOREIGN KEY (department_id) REFERENCES department (id),
    CONSTRAINT asset_drive_type_id_fk
        FOREIGN KEY (drive_type_id) REFERENCES drive_type (id),
    CONSTRAINT asset_location_id_fk
        FOREIGN KEY (location_id) REFERENCES location (id),
    CONSTRAINT asset_manufacturer_id_fk
        FOREIGN KEY (manufacturer_id) REFERENCES manufacturer (id),
    CONSTRAINT asset_memory_type_id_fk
        FOREIGN KEY (memory_type_id) REFERENCES memory_type (id),
    CONSTRAINT asset_model_id_fk
        FOREIGN KEY (model_id) REFERENCES model (id),
    CONSTRAINT asset_operating_system_id_fk
        FOREIGN KEY (operating_system_id) REFERENCES operating_system (id),
    CONSTRAINT asset_processor_id_fk
        FOREIGN KEY (processor_id) REFERENCES processor (id)
);
