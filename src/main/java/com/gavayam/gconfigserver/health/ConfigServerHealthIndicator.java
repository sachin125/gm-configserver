package com.gavayam.gconfigserver.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.stereotype.Component;

@Component
public class ConfigServerHealthIndicator implements HealthIndicator {

    private final EnvironmentRepository environmentRepository;

    public ConfigServerHealthIndicator(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }

    @Override
    public Health health() {
        try {
            // Test if the environment repository can be accessed
            environmentRepository.findOne("test", "default", "default");
            return Health.up()
                .withDetail("message", "Config server is healthy")
                .withDetail("repository", "Git repository accessible")
                .build();
        } catch (Exception e) {
            return Health.down()
                .withDetail("message", "Config server is unhealthy")
                .withDetail("error", e.getMessage())
                .build();
        }
    }
} 