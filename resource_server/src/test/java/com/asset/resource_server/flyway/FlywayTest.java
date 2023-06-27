package com.asset.resource_server.flyway;

import com.asset.resource_server.exception.FlywayTestException;
import jakarta.annotation.Nonnull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto = validate")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FlywayTest {

    @Autowired
    private DataSource dataSource;

    private static final String TEST_SCHEMA_NAME = "resource_server_test_schema";

    private static final Set<String> TABLES = Set.of(
            "asset_type",
            "department",
            "drive_type",
            "location",
            "manufacturer",
            "memory_type",
            "model",
            "operating_system",
            "processor",
            "asset"
    );

    @DisplayName(value = """
            Unit test to check if
            flyway created all the expected tables
            """)
    @Test
    void databaseHasBeenInitialized() {
        GetTableNames getTableNames = new GetTableNames();
        try {
            List<String> getAllTables = JdbcUtils.extractDatabaseMetaData(dataSource, getTableNames);
            Assertions.assertTrue(getAllTables.containsAll(TABLES));
        } catch (MetaDataAccessException e) {
            throw new FlywayTestException(e.getMessage());
        }
    }

    private static class GetTableNames implements DatabaseMetaDataCallback<List<String>> {
        @Nonnull
        @Override
        public List<String> processMetaData(DatabaseMetaData databaseMetaData) throws SQLException {
            ResultSet result = databaseMetaData.getTables(TEST_SCHEMA_NAME, null, null, new String[]{"TABLE"});
            List<String> tableList = new ArrayList<>();

            while (result.next()) {
                tableList.add(result.getString(3));
            }

            return tableList;
        }
    }
}
