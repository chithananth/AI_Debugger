# AI Error Explainer & Fix Generator

An AI-powered beginner-friendly debugging mentor built using React, Spring Boot, MySQL, and AI APIs.

This application helps students and beginner developers understand programming errors in simple language by analyzing code snippets and error messages using AI.

---

# Features

* AI-powered error explanation
* Beginner-friendly debugging guidance
* Technology detection
* Difficulty level classification
* Corrected code suggestions
* Prevention tips
* Learning resource recommendations
* Debugging history storage
* Modern dashboard UI
* Responsive design
* Copy AI response feature

---

# Tech Stack

## Frontend

* React (Vite)
* Axios
* React Markdown
* CSS3

## Backend

* Spring Boot
* Spring Web
* Spring Data JPA
* REST APIs

## Database

* MySQL

## AI Integration

* OpenRouter AI API

---

# Project Architecture

React Frontend
↓
Spring Boot REST API
↓
AI API Integration
↓
MySQL Database

---

# Screenshots



---

# Installation Guide

## Backend Setup

### 1. Clone Repository

```bash
git clone YOUR_GITHUB_LINK
```

### 2. Open Backend Project

Open the Spring Boot project in IntelliJ IDEA.

### 3. Configure MySQL

Create database:

```sql
CREATE DATABASE ai_debugger;
```

### 4. Update application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ai_debugger
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update

openrouter.api.key=YOUR_OPENROUTER_API_KEY
```

### 5. Run Backend

Run:

```text
DebuggerApplication.java
```

Backend runs on:

```text
http://localhost:8080
```

---

# Frontend Setup

## 1. Open Frontend Folder

```bash
cd ai-debugger-frontend
```

## 2. Install Dependencies

```bash
npm install
```

## 3. Run React App

```bash
npm run dev
```

Frontend runs on:

```text
http://localhost:5173
```

---

# API Endpoints

## Explain Error

### POST

```text
http://localhost:8080/debug/explain
```

### Request Body

```json
{
  "codeSnippet": "System.out.println(name.length());",
  "errorMessage": "NullPointerException"
}
```

---

## Get Debug History

### GET

```text
http://localhost:8080/debug
```

---

# Example AI Response

* Difficulty Level
* Technology Detected
* Beginner-Friendly Explanation
* Root Cause
* Corrected Code
* Prevention Tips
* Learning Resources

---

# Future Improvements

* Syntax highlighting
* Monaco code editor
* Export PDF
* JWT authentication
* User login system
* AI analytics dashboard
* Error trend analysis
* Dark/light mode toggle

---

# Learning Outcomes

This project helped in understanding:

* Fullstack application development
* React frontend integration
* Spring Boot backend architecture
* REST API development
* MySQL database integration
* AI API integration
* JSON handling
* MVC architecture
* JPA/Hibernate concepts

---

# Author

Chithanantham M

MCA Student | Fullstack Developer | Java & React Enthusiast

---

# License

This project is developed for educational and portfolio purposes.

