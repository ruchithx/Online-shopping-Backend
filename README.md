# Online Shopping Backend

A microservices-based e-commerce backend application built with Spring Boot, featuring product management, order processing, shopping cart functionality, and API gateway routing.

## 🏗️ Architecture

This project follows a **microservices architecture** pattern with the following services:

```
┌─────────────────────────────────────────────────────────────┐
│                        API Gateway                          │
│                      (Port: 9000)                           │
│              OAuth2 Resource Server + Routing               │
└─────────────────┬───────────────┬──────────────┬────────────┘
                  │               │              │
        ┌─────────▼────┐  ┌──────▼─────┐  ┌────▼──────┐
        │   Product    │  │   Order    │  │   Cart    │
        │   Service    │  │  Service   │  │  Service  │
        │ (Port: 8083) │  │(Port: 8081)│  │(Port: 8082)│
        └──────┬───────┘  └─────┬──────┘  └─────┬─────┘
               │                │               │
        ┌──────▼────────┐ ┌────▼──────┐  ┌─────▼─────┐
        │  PostgreSQL   │ │PostgreSQL │  │PostgreSQL │
        │   (Product)   │ │  (Order)  │  │  (Cart)   │
        └───────────────┘ └───────────┘  └───────────┘
```

## 📦 Services

### 1. **API Gateway** (`/Apigateway`)

- **Port:** 9000
- **Description:** Central entry point for all client requests
- **Features:**
  - Request routing to microservices
  - OAuth2 authentication and authorization
  - JWT token validation
  - Security filtering
- **Technology Stack:**
  - Spring Cloud Gateway MVC
  - Spring Security OAuth2 Resource Server
  - Keycloak integration (realm: `store`)

### 2. **Product Service** (`/Product`)

- **Port:** 8083
- **Description:** Manages product catalog and inventory
- **Features:**
  - Product CRUD operations
  - Product search and filtering
  - Inventory management
- **Database:** PostgreSQL (Neon Cloud)
- **Technology Stack:**
  - Spring Boot 3.3.5
  - Spring Data JPA
  - PostgreSQL Driver
  - ModelMapper
  - Lombok

### 3. **Order Service** (`/Order`)

- **Port:** 8081
- **Description:** Handles order processing and management
- **Features:**
  - Order creation and tracking
  - Order status management
  - Order history
- **Database:** PostgreSQL (Neon Cloud)
- **Technology Stack:**
  - Spring Boot 3.3.5
  - Spring Data JPA
  - PostgreSQL Driver
  - ModelMapper
  - Jackson for JSON processing

### 4. **Cart Service** (`/cart`)

- **Port:** 8082
- **Description:** Manages shopping cart functionality
- **Features:**
  - Add/remove items from cart
  - Update cart quantities
  - Cart persistence
  - Integration with Product Service
- **Database:** PostgreSQL (Neon Cloud)
- **Technology Stack:**
  - Spring Boot 3.3.5
  - Spring Data JPA
  - Spring WebFlux (for reactive communication)
  - PostgreSQL Driver

### 5. **Payment Service** (`/payment`)

- **Status:** In Development
- **Description:** Payment processing service (placeholder)

## 🚀 Getting Started

### Prerequisites

- **Java:** JDK 17 or higher
- **Maven:** 3.6+ (or use included Maven wrapper)
- **Docker:** For containerized deployment
- **Docker Compose:** For orchestrating multiple services

### Installation

1. **Clone the repository:**

   ```bash
   git clone <repository-url>
   cd Online-shopping-Backend
   ```

2. **Build all services:**

   ```bash
   # Build Product Service
   cd Product
   ./mvnw clean install
   cd ..

   # Build Order Service
   cd Order
   ./mvnw clean install
   cd ..

   # Build Cart Service
   cd cart
   ./mvnw clean install
   cd ..

   # Build API Gateway
   cd Apigateway
   ./mvnw clean install
   cd ..
   ```

### Running the Application

#### Option 1: Using Docker Compose (Recommended)

```bash
# Start all services
docker-compose up --build

# Start services in detached mode
docker-compose up -d --build

# Stop all services
docker-compose down
```

#### Option 2: Running Services Individually

```bash
# Terminal 1 - Product Service
cd Product
./mvnw spring-boot:run

# Terminal 2 - Order Service
cd Order
./mvnw spring-boot:run

# Terminal 3 - Cart Service
cd cart
./mvnw spring-boot:run

# Terminal 4 - API Gateway
cd Apigateway
./mvnw spring-boot:run
```

## 🔧 Configuration

### Environment Variables

Each service can be configured using environment variables. Key configurations include:

#### Product Service

- `SPRING_DATASOURCE_URL`: PostgreSQL connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `SERVER_PORT`: Service port (default: 8083)

#### Order Service

- `SPRING_DATASOURCE_URL`: PostgreSQL connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `SERVER_PORT`: Service port (default: 8081)

#### Cart Service

- `SPRING_DATASOURCE_URL`: PostgreSQL connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `PRODUCT_URL`: Product service URL (default: http://localhost:8083)
- `SERVER_PORT`: Service port (default: 8082)

#### API Gateway

- `SERVER_PORT`: Gateway port (default: 9000)
- `PRODUCT_SERVICE_URL`: Product service endpoint
- `ORDER_SERVICE_URL`: Order service endpoint
- `INVENTORY_SERVICE_URL`: Cart service endpoint
- `SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI`: Keycloak issuer URI
- `SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_JWK_SET_URI`: JWT key set URI

### Database Configuration

All services use **PostgreSQL** databases hosted on **Neon Cloud**. Database configurations are managed through environment variables in `docker-compose.yml`.

## 📡 API Endpoints

### API Gateway Routes

All requests should be routed through the API Gateway at `http://localhost:9000`

#### Product Service Routes

- `GET /products` - Get all products
- `GET /products/{id}` - Get product by ID
- `POST /products` - Create new product
- `PUT /products/{id}` - Update product
- `DELETE /products/{id}` - Delete product

#### Order Service Routes

- `GET /orders` - Get all orders
- `GET /orders/{id}` - Get order by ID
- `POST /orders` - Create new order
- `PUT /orders/{id}` - Update order
- `DELETE /orders/{id}` - Delete order

#### Cart Service Routes

- `GET /cart` - Get cart items
- `POST /cart` - Add item to cart
- `PUT /cart/{id}` - Update cart item
- `DELETE /cart/{id}` - Remove item from cart

## 🔐 Security

The application uses **OAuth2** and **JWT** for authentication and authorization:

- **Keycloak** is used as the authorization server
- **Realm:** `store`
- **Client ID:** `storeclient`
- JWT tokens are validated by the API Gateway
- All requests must include a valid JWT token in the Authorization header

### Authentication Flow

1. Client authenticates with Keycloak
2. Keycloak issues JWT token
3. Client includes JWT token in request header: `Authorization: Bearer <token>`
4. API Gateway validates token
5. Request is routed to appropriate microservice

## 🧪 Testing

Each service includes unit and integration tests:

```bash
# Run tests for a specific service
cd <service-directory>
./mvnw test

# Run tests with coverage
./mvnw test jacoco:report
```

### Testing Tools

- **JUnit 5** - Unit testing framework
- **Mockito** - Mocking framework
- **REST Assured** - API testing
- **Spring Boot Test** - Integration testing
- **H2 Database** - In-memory database for testing

## 🛠️ Technology Stack

### Backend Framework

- **Spring Boot 3.3.5**
- **Spring Cloud 2023.0.3**
- **Java 17**

### Data & Persistence

- **Spring Data JPA**
- **PostgreSQL 42.x**
- **Hibernate**

### Security

- **Spring Security**
- **OAuth2 Resource Server**
- **Keycloak**

### Communication

- **Spring Web**
- **Spring WebFlux** (Reactive)
- **REST APIs**

### Build & Deployment

- **Maven**
- **Docker**
- **Docker Compose**

### Utilities

- **Lombok** - Reduce boilerplate code
- **ModelMapper** - Object mapping
- **Jackson** - JSON processing

## 📁 Project Structure

```
Online-shopping-Backend/
├── Apigateway/              # API Gateway service
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── Product/                 # Product management service
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── Order/                   # Order processing service
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── cart/                    # Shopping cart service
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── payment/                 # Payment service (in development)
│   └── src/
├── docker-compose.yml       # Docker orchestration
└── README.md               # This file
```

## 🐳 Docker Support

Each service includes a `Dockerfile` for containerization:

```dockerfile
# Example Dockerfile structure
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE <port>
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## 🔄 Development Workflow

1. **Make changes** to the service code
2. **Build** the service: `./mvnw clean install`
3. **Run tests**: `./mvnw test`
4. **Start service** locally or via Docker
5. **Test endpoints** using Postman or curl
6. **Commit** changes to version control

## 📝 Logging

All services use Spring Boot's default logging configuration:

- **API Gateway:** DEBUG level for security and gateway routing
- **Other Services:** INFO level by default
- Logs include request/response details for debugging

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors

- **Ruchith Samarawickrama** - Initial work

## 🐛 Known Issues

- Payment service is currently under development
- Ensure Keycloak is running on port 8181 for authentication to work
- Database credentials in `docker-compose.yml` should be moved to environment files for production

## 🔮 Future Enhancements

- [ ] Complete Payment Service implementation
- [ ] Add service discovery (Eureka/Consul)
- [ ] Implement distributed tracing (Zipkin/Jaeger)
- [ ] Add API documentation (Swagger/OpenAPI)
- [ ] Implement circuit breakers (Resilience4j)
- [ ] Add caching layer (Redis)
- [ ] Implement event-driven architecture (Kafka/RabbitMQ)
- [ ] Add monitoring and metrics (Prometheus/Grafana)
- [ ] Implement rate limiting
- [ ] Add comprehensive integration tests

## 📞 Support

For support, email [your-email@example.com] or create an issue in the repository.

---

**Happy Coding! 🚀**
