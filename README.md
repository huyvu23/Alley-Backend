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

---

## 🌍 Production Deployment (Render.com)

This project uses **Infrastructure as Code** via a `render.yaml` Blueprint to fully automate deployment on **Render.com**.

### 1. Push to GitHub
Ensure all your latest code, including `Dockerfile` and `render.yaml`, is committed and pushed to your Main/Master branch on GitHub.

### 2. Deploy via Render Blueprint
1. Log in to [Render Dashboard](https://dashboard.render.com).
2. Go to the **Blueprints** tab on the left menu.
3. Click **New Blueprint Instance**.
4. Connect your GitHub repository containing this project.
5. Render will detect the `render.yaml` file and prompt you to enter the required securely synced environment variables (e.g., `DB_URL`, `REDIS_PASSWORD`).
   - *Note: `JWT_SECRET_KEY` will be automatically generated as a secure random value by Render.*
6. Click **Apply** to spin up the service. Any future pushes to GitHub will automatically trigger a new deployment.

### 3. Keep the Free Tier Awake (Optional)
Since Free instances spin down after 15 minutes of inactivity:
1. Go to [cron-job.org](https://cron-job.org/).
2. Create a Free account.
3. Set up a Cronjob that pings your API Documentation URL every 10-14 minutes:
   `https://alley-backend.onrender.com/api/v1/alley/docs`