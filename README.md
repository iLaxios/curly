# Curly

<p align="center">
  <img src="curly_mascot.png" alt="authzilla mascot" width="150"/>
  <br>
  <strong>Meet Curly!</strong>
</p>


**Curly** is a distributed, scalable URL shortening service composed of multiple microservices.. It is designed to handle high-throughput URL creation and redirection with observability and analytics baked in.

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/iLaxios/curly.git
   ```
2. Run using Docker Compose:

   ```bash
   docker-compose up -d
   ```
3. Access services:

    * Shortener API: `http://localhost:8080`
    * Redirect API: `http://localhost:8081`
    * Analytics API: `http://localhost:8083`

---

## Features

* **Shorten URLs**: Generate compact, unique short URLs.
* **Fast redirects**: Optimized with Redis caching for near-instant URL resolution.
* **Distributed analytics**: Track URL creation and clicks asynchronously via Kafka.
* **Persistent storage**: Postgres database stores all URL mappings and analytics.
* **Observability**: Metrics collected via Micrometer, exposed to Prometheus, visualized in Grafana.
* **Scalable & fault-tolerant**: Microservices can be scaled independently; Kafka ensures decoupled communication.

## Architecture

* **Shortener Service**: Handles URL creation, emits `UrlCreatedEvent` to Kafka.
* **Redirect Service**: Resolves short codes to original URLs using Redis cache; emits `UrlClickedEvent`.
* **Analytics Service**: Listens to Kafka events, updates click counts and timestamps.
* **Datastores**: Postgres for persistent storage, Redis for caching.
* **Monitoring**: Prometheus scrapes metrics, Grafana dashboards visualize them.

## Tech Stack

* **Backend**: Java, Spring Boot
* **Database**: Postgres
* **Caching**: Redis
* **Messaging**: Kafka
* **Observability**: Micrometer, Prometheus, Grafana
* **Deployment**: Docker & Docker Compose / Kubernetes ready

