# Alley Backend

Alley Backend is a modern Spring Boot application designed with scalability and performance in mind. This backend provides RESTful APIs for managing tasks and other core functionalities.

## 🚀 Features
- **RESTful API**: Clean and well-structured API endpoints.
- **Authentication**: JWT-based secure authentication.
- **Database**: PostgreSQL for persistent storage with JPA/Hibernate.
- **Caching**: Redis integration for high-performance data caching.
- **Image Management**: Integrated with Cloudinary for seamless media handling.
- **API Documentation**: Interactive and aesthetic documentation powered by **Scalar**.

---

## 🛠️ Prerequisites
Before running the application, ensure you have the following installed:
- **Java 17** or higher
- **Maven** (bundled via `./mvnw`)
- **PostgreSQL**
- **Redis Server**

---

## ⚙️ Setup & Configuration

### 1. Database Configuration
Update `src/main/resources/application.properties` with your PostgreSQL credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/alley
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 2. Redis Configuration
Ensure your Redis server is running:
```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### 3. Cloudinary Setup
Set your Cloudinary API keys:
```properties
cloudinary.cloud_name=your_cloud_name
cloudinary.api_key=your_api_key
cloudinary.api_secret=your_api_secret
```

---

## 📖 API Documentation (Scalar)

We use **Scalar** to generate modern, interactive API documentation. It is built upon the **OpenAPI 3** specification generated automatically by **SpringDoc**.

### Accessing the Documentation
Once the server is running, you can access the API reference at:
[http://localhost:8080/api/v1/alley/docs](http://localhost:8080/api/v1/alley/docs)

### How it was set up:
1.  **Dependencies**: Added `springdoc-openapi-starter-webmvc-api` for spec generation and `scalar-webmvc` for the UI.
2.  **Configuration**: 
    - `scalar.url`: Points to the local OpenAPI JSON spec (`/api/v1/alley/v3/api-docs`).
    - `scalar.path`: Custom UI endpoint set to `/docs`.
3.  **Security**: Configured `SecurityConfig.java` to allow public access to `/docs/**` and `/v3/api-docs/**`.

---

## 🏃 How to Run

1.  **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd Alley-Backend
    ```

2.  **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

3.  **Verify the API**:
    Access the base API at `http://localhost:8080/api/v1/alley`.

---

## 🧪 Testing
Run unit and integration tests using:
```bash
./mvnw test
```