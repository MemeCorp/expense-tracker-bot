package org.meme.corp.database.repository;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.meme.corp.database.repository.configuration.PostgresqlContainer;
import org.meme.corp.database.repository.impl.PersonClientRepository;
import org.testcontainers.containers.PostgreSQLContainer;

public class AbstractRepositoryTest {

    private static final String MIGRATIONS_LOCATION = "db/migrations";
    private static final String MIGRATIONS_SCHEMAS = "public";

    private static final PostgreSQLContainer<PostgresqlContainer> postgreSQLContainer = PostgresqlContainer.getNewInstance();

    @BeforeAll
    public static void setUp() {
        postgreSQLContainer.start();
        var flyway = Flyway.configure()
                .locations(MIGRATIONS_LOCATION)
                .schemas(MIGRATIONS_SCHEMAS)
                .dataSource(postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword())
                .load();
        flyway.info();
        flyway.migrate();
    }

}
