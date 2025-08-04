# Gavayam Config Server

A production-ready Spring Cloud Config Server for centralized configuration management.

## 🚀 Features

- **Centralized Configuration Management**: Store and serve configuration from Git repositories
- **Security**: Authentication and authorization with Spring Security
- **Monitoring**: Health checks, metrics, and distributed tracing
- **Resilience**: Circuit breakers and retry mechanisms
- **Containerization**: Docker support with multi-stage builds
- **Production Ready**: Optimized for production deployment

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker (for containerized deployment)
- Git repository for configuration storage

## 🛠️ Quick Start

### Local Development

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd gm-configserver
   ```

2. **Build the application**
   ```bash
   ./mvnw clean package
   ```

3. **Run the application**
   ```bash
   java -jar target/gm-gconfigserver-0.0.1-SNAPSHOT.jar
   ```

### Docker Deployment

1. **Build the Docker image**
   ```bash
   docker build -t gm-config-server .
   ```

2. **Run with Docker Compose**
   ```bash
   docker-compose up -d
   ```

## 🔧 Configuration

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `ENCRYPT_KEY` | Encryption key for sensitive data | `45D81EC1EF61DF9AD8D3E5BB397F9` |
| `GIT_URI` | Git repository URL | `https://github.com/sachin125/gavayam-config.git` |
| `GIT_DEFAULT_LABEL` | Git branch/tag | `main` |
| `EUREKA_URL` | Eureka server URL | `http://localhost:9002/eureka` |
| `CONFIG_USER` | Config user username | `configuser` |
| `CONFIG_PASSWORD` | Config user password | `config123` |
| `ADMIN_USER` | Admin username | `admin` |
| `ADMIN_PASSWORD` | Admin password | `admin123` |

### Security

The application uses Spring Security with:
- Basic authentication for all endpoints
- Role-based access control
- Admin role for actuator endpoints
- User role for configuration access

**Default Credentials:**
- Admin: `admin` / `admin123`
- User: `configuser` / `config123`

## 📊 Monitoring & Health Checks

### Health Endpoints

- **Health Check**: `GET /gconfigserver/actuator/health`
- **Info**: `GET /gconfigserver/actuator/info`
- **Metrics**: `GET /gconfigserver/actuator/metrics`
- **Prometheus**: `GET /gconfigserver/actuator/prometheus`

### Custom Health Indicators

- Git repository connectivity
- Configuration loading status
- Application health status

## 🧪 Testing

### Run Tests
```bash
./mvnw test
```

### Test Coverage
```bash
./mvnw jacoco:report
```

## 🚀 Production Deployment

### 1. Security Considerations

- **Change default passwords** in production
- **Use external secret management** (HashiCorp Vault, AWS Secrets Manager)
- **Enable HTTPS** with proper certificates
- **Configure firewall rules** appropriately

### 2. Monitoring Setup

- **Prometheus**: Configure scraping from `/actuator/prometheus`
- **Grafana**: Create dashboards for metrics visualization
- **Alerting**: Set up alerts for health check failures
- **Logging**: Configure log aggregation (ELK stack, Splunk)

### 3. High Availability

- **Multiple instances**: Deploy multiple config server instances
- **Load balancer**: Use a load balancer for traffic distribution
- **Database**: Consider using a database backend for configuration storage
- **Backup**: Regular backups of configuration repositories

### 4. Performance Optimization

- **JVM tuning**: Adjust heap size and GC settings
- **Caching**: Enable configuration caching
- **Connection pooling**: Optimize database connections
- **Compression**: Enable response compression

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/gavayam/gconfigserver/
│   │       ├── GmConfigServerApplication.java
│   │       ├── config/
│   │       │   └── SecurityConfig.java
│   │       └── health/
│   │           └── ConfigServerHealthIndicator.java
│   └── resources/
│       ├── application.yaml
│       └── application-prod.yaml
└── test/
    ├── java/
    │   └── com/gavayam/gconfigserver/
    │       └── ConfigServerApplicationTests.java
    └── resources/
        └── application-test.yaml
```

## 🔍 Troubleshooting

### Common Issues

1. **Git repository access issues**
   - Verify repository URL and credentials
   - Check network connectivity
   - Ensure repository exists and is accessible

2. **Authentication failures**
   - Verify username/password
   - Check security configuration
   - Ensure proper role assignments

3. **Health check failures**
   - Check Git repository connectivity
   - Verify configuration loading
   - Review application logs

### Logs

Application logs are written to:
- Console: Standard output
- File: `/app/logs/gm-config-service.log` (in container)

## 📈 Metrics

Key metrics to monitor:
- `http.server.requests`: HTTP request metrics
- `process.cpu.usage`: CPU usage
- `jvm.memory.used`: Memory usage
- `config.server.health`: Health check status

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License. 