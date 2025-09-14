# BookMyMovie 🎬

Backend Services for a Movie Booking Application

---

## Table of Contents

- [About](#about)  
- [Architecture & Components](#architecture--components)  
- [Prerequisites](#prerequisites)

---

## About

BookMyMovie is a microservices-based backend system for managing movie ticket bookings. It handles user authentication, movie show listings, payments, and inter-service communication via events.  

Main goals:

- Decouple functionality via separate services  
- Ensure secure user authentication & authorization  
- Reliable and scalable payment processing  

---

## Architecture & Components

The system is split into several services:

| Service | Responsibility |
|---|---|
| **gateway** | API gateway / routing |
| **securityservice** | Handles user login / signup / authentication / authorization |
| **payments** | Processes payment logic, status, etc. |
| **events** | Creating and Assigning events and venue service. |


---

## Prerequisites

Make sure you have:

- Java
- Gradle
- Database - PostgreSQL
- Caching - Redis 
