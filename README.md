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

This project is configured for easy deployment on **Render** using **Docker**.

### 1. GitHub Setup
- Push your latest code (including the `Dockerfile`) to your GitHub repository.

### 2. Render Web Service Setup
1. Log in to [Render.com](https://render.com/).
2. Click **New +** > **Web Service**.
3. Connect your GitHub repository.
4. **Runtime**: Select `Docker`.
5. **Instance Type**: Select `Free`.

### 3. Environment Variables (Required)
In the **Environment** tab on Render, add these variables to connect your services:

| Key | Example Value (Supabase/Upstash) |
| :--- | :--- |
| `DB_URL` | `jdbc:postgresql://db.xxxx.supabase.co:5432/postgres` |
| `DB_USERNAME` | `postgres` |
| `DB_PASSWORD` | `your_db_password` |
| `REDIS_HOST` | `your-redis-host.upstash.io` |
| `REDIS_PORT` | `6379` |
| `REDIS_PASSWORD` | `your_redis_password` |
| `JWT_SECRET_KEY` | `your_long_random_secret_string` |
| `CLOUDINARY_CLOUD_NAME` | `your_cloud_name` |
| `CLOUDINARY_API_KEY` | `your_api_key` |
| `CLOUDINARY_API_SECRET` | `your_api_secret` |

### 4. How to keep it Always-On
Since the Free Tier "sleeps" after 15 minutes of inactivity:
1. Go to [cron-job.org](https://cron-job.org/).
2. Create a new Cronjob that pings your Scalar docs URL every 10-14 minutes:
   `https://your-app-name.onrender.com/api/v1/alley/docs`