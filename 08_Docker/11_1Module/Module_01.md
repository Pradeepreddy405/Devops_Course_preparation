# Dockerfile -- Complete Guide & Best Practices

## Overview

This README explains Dockerfiles, their instructions, image building &
tagging, Docker layers and caching, and production-level Dockerfile
optimization best practices.

## 1. What is a Dockerfile?

A Dockerfile is a text file that contains a set of instructions to
automatically build a Docker image. It defines how an application and
its environment are packaged, ensuring consistent execution across
environments.

### What a Dockerfile does

-   Specifies the base image
-   Installs dependencies
-   Copies application code
-   Configures environment variables
-   Defines how the container starts
-   Creates immutable, cached layers

## 2. Dockerfile Instructions

FROM, RUN, COPY, ADD, WORKDIR, CMD, ENTRYPOINT, ENV, EXPOSE

## 3. Building and Tagging Images

Images are built using docker build and tagged for versioning and CI/CD
traceability.

## 4. Understanding Layers and Caching

Docker images are built as immutable layers. Docker cache reuses
unchanged layers to speed up builds.

## 5. Best Practices

-   Use minimal base images
-   Use multi-stage builds
-   Minimize layers
-   Avoid root user
-   Use .dockerignore
-   Prefer COPY over ADD

## Summary

Dockerfile optimization improves security, performance, and scalability
of CI/CD pipelines.
