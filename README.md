# GetSetLearn - Online Learning Platform (Case Study)
React (Vite) + Spring Boot / Spring MVC + MySQL

## Run locally
1. Start MySQL. Local password is set in backend/src/main/resources/application.properties (default root / Root1234).
2. Backend:  cd backend && mvn spring-boot:run     (http://localhost:8080)
3. Frontend: cd frontend && npm install && npm run dev   (http://localhost:5173)

## Features
Landing page, register (name, DOB, mobile, email, strong password), login, 5 courses (AI, FSD, CN, ML, CC),
enroll / unenroll, 5 units per course, Unit 1 test (10 questions) for each course, colour-coded dashboard.

## Structure
backend/  controller, model, repository (Spring MVC + Spring Data JPA), SeedData.java holds courses, units and questions
frontend/ React components: Landing, AuthForm, Courses, Units, Test, Dashboard

## Deploy
Database: Aiven MySQL | Backend: Render (backend/Dockerfile) | Frontend: Vercel (root = frontend)
Backend env vars: DB_URL, DB_USER, DB_PASSWORD, CORS_ORIGIN   Frontend env var: VITE_API_URL
