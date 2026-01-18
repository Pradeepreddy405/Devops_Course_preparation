# Docker Fundamentals

This README provides a clear and interview-ready overview of **Docker Fundamentals**, suitable for **DevOps / Cloud / Platform Engineer roles (up to ~20 LPA)**.

---

## 1. What is Docker?

Docker is an **open-source containerization platform** used to **build, ship, and run applications** in isolated environments called **containers**.

Containers package:
- Application code
- Dependencies
- Runtime

into a **single, lightweight, and portable unit**, ensuring consistency across **development, testing, and production environments**.

Docker also provides a **rich ecosystem** including **Docker Engine, Docker CLI, and Docker Hub** for seamless container management.

### Docker Components

- **Docker Engine** – Runs and manages containers.
- **Docker CLI** – Command-line interface used to interact with Docker Engine.
- **Docker Hub** – Image registry to store and share Docker images.

---

## 2. Containers vs Virtual Machines

### Virtual Machines

- Virtualization allows running **multiple isolated operating systems** on a single physical machine.
- **Core component: Hypervisor**, which creates and manages VMs.
- Each VM runs a **full guest OS**, fully isolated from the host and other VMs.

### Containers

- Containers are **lightweight environments** that package the application, dependencies, and runtime.
- Containers **share the host OS kernel** and provide **process-level isolation**.

### Key Comparison

| Feature | Containers | Virtual Machines |
|-------|-----------|------------------|
| OS | Share host OS kernel | Full guest OS per VM |
| Size | Lightweight (MBs) | Heavy (GBs) |
| Startup Time | Seconds | Minutes |
| Isolation | Process-level | Hardware-level |
| Best Use Case | Microservices, CI/CD | Legacy & security-sensitive workloads |

**Note:** In modern cloud architectures, containers often run **inside VMs** to balance efficiency and isolation.

---

## 3. Docker Architecture
    
          ![Docker Architecture](03_Docker_architecture.png)
      
Docker follows a **client-server architecture** with three main components.

### 1. Docker Engine

- Core component of Docker.
- Includes:
  - **Docker Daemon (`dockerd`)** – Manages images, containers, networks, and volumes.
  - **REST API** – Enables communication between the CLI and daemon.
- Responsible for **building, running, and managing containers**.

### 2. Docker Client

- Command-line interface (`docker run`, `docker build`, etc.).
- Sends requests to Docker Engine via the **REST API**.

### 3. Docker Hub

- Central **image registry**.
- Used to **pull images** to run containers or **push images** to share.

---

## 4. Installing Docker (Linux / Windows / Mac)

Official Docker installation guide:

https://docs.docker.com/engine/install/

---

## 5. Basic Docker CLI Commands

```bash
docker --version   # Check Docker version
docker run         # Create and start a container
docker ps          # List running containers
docker images      # List Docker images
docker stop        # Stop a running container
docker rm          # Remove a stopped container
```

---

## 6. Docker Container Lifecycle

A Docker container goes through multiple stages from creation to deletion.

    ![Docker Architecture](06_Contaier_lifecycle.png)

### Lifecycle Flow

```
Image → Create → Start / Run → Running → Pause / Unpause → Stop / Kill → Exited → Remove
```

### Lifecycle Stages

#### Stage 1: Create (`docker create`)
Creates a container from an image without running it.

```bash
docker create <image>
```

#### Stage 2: Start (`docker start`)
Starts a stopped or newly created container.

```bash
docker start <container_id>
```

#### Stage 3: Run (`docker run`)
Creates and starts a container in a single step.

```bash
docker run <image>
```

#### Stage 4: Pause / Unpause (`docker pause` / `docker unpause`)
Temporarily suspends or resumes container processes.

```bash
docker pause <container>
docker unpause <container>
```

#### Stage 5: Stop (`docker stop`)
Gracefully stops a container (SIGTERM → SIGKILL).

```bash
docker stop <container>
```

#### Stage 6: Restart (`docker restart`)
Stops and starts a container in one command.

```bash
docker restart <container>
```

#### Stage 7: Kill (`docker kill`)
Immediately stops a container using SIGKILL.

```bash
docker kill <container>
```

#### Stage 8: Remove (`docker rm`)
Deletes a stopped container and releases resources.

```bash
docker rm <container>
```

---

## Lifecycle Summary (Interview Ready)

A Docker container lifecycle starts with creation from an image, then it can be started or run, paused/unpaused, stopped, restarted, or killed, and finally removed. Containers are **ephemeral**, so persistent data should be stored using **Docker volumes**.

---

## Conclusion

These Docker fundamentals cover the **core concepts required for DevOps and Cloud interviews** and form the foundation for advanced topics such as **Docker networking, volumes, multi-stage builds, and Kubernetes**.

---

Happy Learning 🚀

