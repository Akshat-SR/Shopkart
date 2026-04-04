# 🛒 Shopkart: Backend E-Commerce System
A full-featured e-commerce backend built using **Spring Boot**, implementing **JWT-based authentication**, **role-based authorization**, and core features like cart management, order processing, and admin controls.

---

## 🚀 Features

- 🔐 JWT Authentication & Authorization
- 👤 User Registration & Login
- 🛒 Cart Management (Add / Remove Items)
- 📦 Order Placement with Address
- 🏠 Address Management
- 👑 Admin Panel (Product Management & Order Status)
- 🔄 Order Lifecycle (PENDING → SHIPPED → DELIVERED)
- 📄 Swagger API Documentation

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Hibernate / JPA
- MySQL
- Swagger (OpenAPI)

---

## ⚙️ Setup Instructions

### 1. Clone the repository

```
git clone https://github.com/Akshat-SR/Shopkart.git
cd your-repo-name
```

---

### 2. Configure Database

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

### 3. Run the Application

```
mvn spring-boot:run
```

Server will start at:

```
http://localhost:8080
```

---

## 🔐 Authentication

All protected endpoints require JWT token:

```
Authorization: Bearer <your_token>
```

---

## 📌 API Endpoints

---

### 👤 User APIs

#### Register

```
POST /api/users/register
```

**Body:**

```
{
  "name": "John",
  "email": "john@example.com",
  "password": "1234"
}
```

---

#### Login

```
POST /api/users/login
```

**Body:**

```
{
  "email": "john@example.com",
  "password": "1234"
}
```

**Response:**

```
{
  "token": "JWT_TOKEN"
}
```

---

### 🛒 Cart APIs

#### Add to Cart

```
POST /api/cart/add?productId=1&quantity=2
```

---

#### Get Cart

```
GET /api/cart
```

---

#### Remove Item

```
DELETE /api/cart/remove?productId=1
```

---

### 🏠 Address APIs

#### Add Address

```
POST /api/address
```

**Body:**

```
{
  "street": "MG Road",
  "city": "Pune",
  "state": "Maharashtra",
  "pincode": "411001"
}
```

---

#### Get Addresses

```
GET /api/address
```

---

### 📦 Order APIs

#### Place Order

```
POST /api/orders?addressId=1
```

---

#### Get User Orders

```
GET /api/orders
```

---

### 👑 Admin APIs

#### Create Product

```
POST /api/admin/products
```

---

#### Delete Product

```
DELETE /api/admin/products/{id}
```

---

#### Update Order Status

```
PUT /api/admin/orders/{orderId}/status?status=SHIPPED
```

Allowed values:

```
PENDING, SHIPPED, DELIVERED
```

---

## 🔄 Order Flow

```
User:
Register → Login → Add to Cart → Add Address → Place Order

Admin:
Login → Manage Products → Update Order Status
```

---

## 📄 Swagger Documentation

Access API docs:

```
http://localhost:8080/swagger-ui/index.html
```

---

## ⚠️ Important Notes

- All protected endpoints require JWT token
- Admin endpoints require `ROLE_ADMIN`
- Users can only access their own cart and orders
- Order requires a valid `addressId`

---

## 📌 Future Improvements

- Payment Integration
- Product Images (Cloudinary)
- Review & Rating System
- Frontend Integration

---

## 👨‍💻 Author

Akshat Srivastava
GitHub: https://github.com/Akshat-SR

---
