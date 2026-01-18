#  Docker Commands – Quick Reference README

This README provides a **clean, categorized reference of commonly used Docker commands**. Useful for beginners, DevOps engineers, and quick revision before interviews or production work.

---

##  Docker Information Commands

```bash
docker --version            # Check Docker version
docker info                 # Docker system-wide information
docker help                 # List all Docker commands
```

---

##  Image Commands

```bash
docker images               # List all local images
docker pull nginx           # Download image from registry
docker build -t myapp .     # Build image from Dockerfile
docker rmi image_id         # Delete image
docker tag img:v1 img:v2    # Tag image
```

---

##  Container Commands

```bash
docker ps                   # List running containers
docker ps -a                # List all containers
docker run nginx            # Run a container
docker run -d nginx         # Run container in detached mode
docker run -it ubuntu bash  # Run interactive container
docker stop container_id    # Stop container
docker start container_id   # Start container
docker restart container_id # Restart container
docker rm container_id      # Delete container
```

---

##  Inspect & Debug

```bash
docker logs container_id       # View container logs
docker exec -it container bash # Enter running container
docker inspect container_id    # Detailed container info
docker stats                   # Live resource usage
```

---

##  Networking Commands

```bash
docker network ls                 # List networks
docker network create mynet       # Create a network
docker network inspect mynet      # Network details
docker network rm mynet           # Remove network
```

---

##  Volume & Storage

```bash
docker volume ls                   # List volumes
docker volume create myvol         # Create volume
docker run -v myvol:/data nginx    # Attach volume
docker volume inspect myvol        # Volume details
docker volume rm myvol             # Remove volume
```

---

##  Docker Compose

```bash
docker-compose up                 # Start services
docker-compose up -d              # Start in detached mode
docker-compose down               # Stop services
docker-compose ps                 # Service status
docker-compose logs               # View logs
```

---

##  Cleanup Commands (Avoid Disk Full Issues)

```bash
docker system df                  # Disk usage
docker system prune               # Remove unused data
docker container prune            # Remove stopped containers
docker image prune                # Remove unused images
docker volume prune               # Remove unused volumes
```

---

##  Registry & Push (DockerHub / Nexus)

```bash
docker login
docker tag myapp user/myapp:v1
docker push user/myapp:v1
docker pull user/myapp:v1
```

---

##  Notes
- Containers are **ephemeral**; use volumes for persistence
- Prefer **Docker Compose** for multi-container apps
- Regular cleanup avoids disk space issues

---



