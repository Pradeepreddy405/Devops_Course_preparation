# Kubernetes Module 3 — Services & Networking

## Overview
This module focuses on **Kubernetes Services & Networking**, providing a solid understanding of how Pods communicate **internally** and **externally**, along with hands-on experience using different Service types. It covers real-world networking patterns, DNS, and best practices for exposing applications.

---

## Table of Contents
1. [Why Services are Needed](#why-services-are-needed)  
2. [Pod-to-Pod Communication](#pod-to-pod-communication)  
3. [Kubernetes Service Types](#kubernetes-service-types)  
   - [ClusterIP](#clusterip)  
   - [NodePort](#nodeport)  
   - [LoadBalancer](#loadbalancer)  
4. [kube-proxy Basics](#kube-proxy-basics)  
5. [DNS in Kubernetes](#dns-in-kubernetes)  
6. [Hands-On Lab](#hands-on-lab)  
7. [Best Practices](#best-practices)  

---

## Why Services are Needed
Pods are **ephemeral** and **IP addresses change dynamically**.  
Services provide:

- A **stable network identity** (IP + DNS)  
- **Load balancing** across multiple Pods  
- Decoupling **consumers from the underlying Pods**

> Example: A frontend Pod communicates with a backend service without worrying about backend Pod IP changes.

---

## Pod-to-Pod Communication
Kubernetes networking model guarantees:

- Each Pod has a **unique IP**  
- Pods can communicate across nodes **without NAT**  
- Direct Pod-to-Pod communication simplifies microservice patterns

```bash
# Example: curl from one Pod to another
kubectl run test-pod --image=busybox -it --rm -- sh
wget -qO- http://backend-service
```

---

## Kubernetes Service Types

### ClusterIP (Internal Access)
- Default Service type  
- Only accessible **inside the cluster**  
- Provides a **stable ClusterIP and DNS name**

```bash
kubectl expose deployment nginx-deploy --name=nginx-service --port=80 --target-port=80
kubectl get svc nginx-service
```

### NodePort (External Access)
- Exposes service on **every node’s IP**  
- Port range: `30000–32767`  
- Accessible from outside the cluster: `http://<NodeIP>:<NodePort>`

```bash
kubectl expose deployment nginx-deploy --type=NodePort --name=nginx-nodeport --port=80
kubectl get svc nginx-nodeport
```

> ⚠️ Requires firewall/security group rules to allow NodePort traffic.

### LoadBalancer (Cloud Managed)
- For cloud environments (AWS, GCP, Azure)  
- Automatically provisions **external IP** via cloud LB  
- Flow: `User → LoadBalancer → Service → Pods`

```bash
kubectl expose deployment nginx-deploy --type=LoadBalancer --name=nginx-lb --port=80
```

---

## kube-proxy Basics
- Runs on **all nodes**  
- Maintains **network rules** to forward traffic from Service → Pods  
- Supports **iptables** or **IPVS** mode for load balancing

> Without kube-proxy, Service networking would fail.

---

## DNS in Kubernetes
- CoreDNS provides **automatic name resolution**  
- Service DNS format:  
```
<service-name>.<namespace>.svc.cluster.local
```
- Pods can access services using just the **service name**:

```bash
curl http://nginx-service
```

---

## Hands-On Lab

### 1. Deploy Nginx Deployment

```bash
kubectl create deployment nginx-deploy --image=nginx --replicas=3
kubectl get pods
```

### 2. ClusterIP Service

```bash
kubectl expose deployment nginx-deploy --name=nginx-service --port=80
kubectl get svc nginx-service
```

### 3. NodePort Service

```bash
kubectl expose deployment nginx-deploy --type=NodePort --name=nginx-nodeport --port=80
kubectl get svc nginx-nodeport
```

- Access from EC2:  
```bash
curl http://<EC2_PUBLIC_IP>:<NodePort>
```

### 4. LoadBalancer Service (Cloud)

```bash
kubectl expose deployment nginx-deploy --type=LoadBalancer --name=nginx-lb --port=80
kubectl get svc nginx-lb
```

- Access via **external IP** assigned by cloud provider

---

## Best Practices

- Use **ClusterIP** for internal microservices  
- Use **NodePort only for testing or Dev** environments  
- Use **LoadBalancer** for production workloads  
- **DNS names** are preferred over IP addresses  
- Always monitor **kube-proxy logs** if Service traffic fails  
- Open only required **NodePort ports** in firewall/security groups  

---

## Diagram: Service Traffic Flow

```
External User
     |
     v
+-----------------+         +-------------------+
|   NodePort/LB    | ----->  |      Service      | -----> Pods
+-----------------+         +-------------------+
                                      |
                                      v
                                  Backend Pods
```

---

## Conclusion
By completing this module, you now understand:

- Why Kubernetes Services exist  
- Internal vs external communication  
- How to use ClusterIP, NodePort, and LoadBalancer  
- Role of kube-proxy and DNS in service discovery  
- Real-world considerations for AWS/EC2 or cloud environments

This foundation is critical for **production-ready Kubernetes deployments**.

