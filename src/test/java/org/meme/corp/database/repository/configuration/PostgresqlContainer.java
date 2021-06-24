package org.meme.corp.database.repository.configuration;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresqlContainer extends PostgreSQLContainer<PostgresqlContainer> {

    private static final String CONTAINER_IMAGE_VERSION = "postgres:latest";
    private static final String CONTAINER_DB_NAME = "expense_tracker_db";
    private static final String CONTAINER_USER_NAME = "admin";
    private static final String CONTAINER_USER_PASSWORD = "postgres";

    private static final String ENV_URL_KEY = "hibernate_url";
    private static final String ENV_USER_KEY = "hibernate_user";
    private static final String ENV_PASSWORD_KEY = "hibernate_password";

    private PostgresqlContainer() {
        super(CONTAINER_IMAGE_VERSION);
    }

    public static PostgresqlContainer getNewInstance() {
        return new PostgresqlContainer()
                .withDatabaseName(CONTAINER_DB_NAME)
                .withPassword(CONTAINER_USER_NAME)
                .withUsername(CONTAINER_USER_PASSWORD);
    }

    @Override
    public void start() {
        super.start();
        System.setProperty(ENV_URL_KEY, super.getJdbcUrl());
        System.setProperty(ENV_USER_KEY, super.getUsername());
        System.setProperty(ENV_PASSWORD_KEY, super.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}