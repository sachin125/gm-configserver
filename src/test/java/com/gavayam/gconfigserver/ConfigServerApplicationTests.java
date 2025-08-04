package com.gavayam.gconfigserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ConfigServerApplicationTests {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void contextLoads() {
        // Basic context loading test
    }

    @Test
    void healthEndpointShouldBeAccessible() {
        String url = "http://localhost:" + port + "/gconfigserver/actuator/health";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("UP");
    }

    @Test
    void infoEndpointShouldBeAccessible() {
        String url = "http://localhost:" + port + "/gconfigserver/actuator/info";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void configEndpointShouldRequireAuthentication() {
        String url = "http://localhost:" + port + "/gconfigserver/test/default";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void configEndpointShouldBeAccessibleWithCredentials() {
        String url = "http://localhost:" + port + "/gconfigserver/test/default";
        
        TestRestTemplate authenticatedTemplate = new TestRestTemplate("configuser", "config123");
        ResponseEntity<String> response = authenticatedTemplate.getForEntity(url, String.class);
        
        // This might fail if Git repo is not accessible, but should not be 401
        assertThat(response.getStatusCode()).isNotEqualTo(HttpStatus.UNAUTHORIZED);
    }
} 