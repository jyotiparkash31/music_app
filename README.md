# Music App Backend

A robust backend service for a music streaming application built with Spring Boot and Spring Security, featuring JWT authentication and MySQL database integration.

## Features

- **User Authentication**: Secure JWT-based authentication system
- **User Management**: User registration, login, and profile management
- **Song Management**: CRUD operations for songs
- **Playlist Management**: Create, update, and manage playlists
- **Secure API Endpoints**: Role-based access control for protected routes
- **RESTful API**: Clean and consistent API design

## Tech Stack

- **Backend Framework**: Spring Boot 3.5.0
- **Security**: Spring Security with JWT
- **Database**: MySQL
- **Build Tool**: Maven
- **Java Version**: 17
- **Lombok**: For reducing boilerplate code
- **JPA/Hibernate**: For database operations

## Prerequisites

- Java 17 or later
- MySQL 8.0 or later
- Maven 3.6.3 or later
- Git (optional)

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/music-app-backend.git
   cd music-app-backend
   ```

2. **Configure Database**
   - Create a MySQL database named `music_app_db`
   - Update the database configuration in `src/main/resources/application.properties`

3. **Build the application**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, you can access the following:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Documentation**: `http://localhost:8080/v3/api-docs`

## Project Structure

```
src/main/java/com/musicapp/music_app_backend/
├── config/           # Configuration classes
├── controller/       # REST controllers
├── dto/              # Data Transfer Objects
├── model/            # Entity classes
├── repository/       # Data access layer
├── security/         # Security configuration and JWT
└── service/          # Business logic layer
```

## API Endpoints

### Authentication
- `POST /api/auth/signup` - Register a new user
- `POST /api/auth/signin` - Authenticate user and get JWT token

### Users
- `GET /api/users/me` - Get current user profile
- `PUT /api/users/me` - Update user profile

### Songs
- `GET /api/songs` - Get all songs
- `GET /api/songs/{id}` - Get song by ID
- `POST /api/songs` - Create a new song (Admin only)
- `PUT /api/songs/{id}` - Update a song (Admin only)
- `DELETE /api/songs/{id}` - Delete a song (Admin only)

### Playlists
- `GET /api/playlists` - Get user's playlists
- `POST /api/playlists` - Create a new playlist
- `GET /api/playlists/{id}` - Get playlist by ID
- `PUT /api/playlists/{id}` - Update a playlist
- `DELETE /api/playlists/{id}` - Delete a playlist
- `POST /api/playlists/{id}/songs` - Add song to playlist
- `DELETE /api/playlists/{id}/songs/{songId}` - Remove song from playlist

## Environment Variables

Create a `src/main/resources/application.properties` file with the following content:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/music_app_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# JWT Configuration
app.jwtSecret=your_jwt_secret_key_here
app.jwtExpirationMs=86400000  # 24 hours
```

## Testing

Run the test suite with:

```bash
mvn test
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Spring Boot Team
- JWT for authentication
- MySQL Community
- All open-source contributors

## Support

For support, please open an issue on the GitHub repository.
