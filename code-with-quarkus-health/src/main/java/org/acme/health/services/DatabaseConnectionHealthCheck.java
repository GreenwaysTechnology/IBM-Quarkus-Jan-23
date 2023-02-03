package org.acme.health.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Readiness
public class DatabaseConnectionHealthCheck implements HealthCheck {
    @ConfigProperty(name = "database.up", defaultValue = "false")
    boolean databaseup;

    @Override
    public HealthCheckResponse call() {
        //get Db connection and test connection is active or not.
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Database Connection HealthCheck");
        //simulate database is up and running
        try {
            simulateDatabaseConnection();
            return responseBuilder.up().build();
        } catch (IllegalStateException exception) {
            return responseBuilder.down().withData("error", exception.getMessage()).build();
        }
    }

    private void simulateDatabaseConnection() {
        if (!databaseup) {
            throw new IllegalStateException("Cant connect database");
        }

    }

}
