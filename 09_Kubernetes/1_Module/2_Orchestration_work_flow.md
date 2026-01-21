### 1)	What is Kubernetes Orchestration?
Kubernetes orchestration is how Kubernetes automatically deploys, schedules, scales, heals, and manages containerized applications across a cluster of machines (nodes).


### 2) Kubernetes Architecture

	Cluster    : A Kubernetes cluster is a collection of control plane and worker nodes that work together to deploy, manage, scale, and heal containerized 
				 applications.
				 
			   : Cluster consists of two important components i.e nothing but Control plane and worker nodes.
			   
				 	Cluster =  Control plane + worker  nodes 
					
			    	1) Control plane components (API Server , Scheduler , Control manager and etcd(cluster state database) . 
					2) Worker node components   (Kubelet, proxy, CNI (Networking),Container run time
	
<img src="Images/00_Kubernetes_Architecture_002.png" width="700"/>

1)Control plane components:
	
		1)API Server (Entry Point)
			- Receives all requests (kubectl, UI, CI/CD)
			- Validates YAML
			- Authenticates & authorizes
			- Stores desired state in etcd
			- Nothing in Kubernetes happens without the API Server

	    2)etcd (Memory of the Cluster)
			
			Stores:
			Desired state (replicas, images, configs)
			Actual cluster metadata
			Acts as single source of truth
	
			Example:
			
				Desired replicas = 3
				Controller Manager (Reconciliation Engine)
				
				Continuously checks:
				Desired state (etcd) VS Actual state (nodes)
			
				If mismatch:
				Takes corrective action
			
					Example:
					
						Desired Pods = 3
						Running Pods = 2
						Controller creates 1 new Pod

		3)Scheduler:
		
			- Picks which worker node should run the Pod
			- Considers:
				CPU & memory availability
				Node labels / taints
			- Caution:
				Scheduler does not run Pods
				It only assigns Pods to nodes
	
	
2)Workernode(data plane) :

		1)kubelet: Pod lifecycle manager
		
			- Talks to the API Server
			- Creates, starts, stops Pods on the node
			- Ensures containers match the desired state
			- Performs health checks (liveness/readiness probes)
			- Reports node & pod status back to control plane	
			
		
		2)kube-proxy: Networking & traffic manager (per node)
			- Implements Services (ClusterIP / NodePort / LoadBalancer)
			- Handles network traffic routing to Pods
			- Uses iptables / IPVS rules
			- Load-balances traffic across healthy Pods
			
		3)Container Runtime(containerd,CRI-O) : with run time applications will run inside the pods
			- Responsible for running containers
			- Pulls container images from registries
			- Creates and starts containers inside Pods
			- Stops and deletes containers when required
			- Works with kubelet via CRI (Container Runtime Interface)
		4)Container Runtime — Container execution engine

		5)CNI (Container Network Interface) — Pod networking provider
			- Calico
			- Flannel
			- Cilium
			- Assigns IP addresses to Pods
			- Enables Pod-to-Pod communication
			- Enables Pod-to-Service communication
			- Handles cross-node networking
			- Enforces Network Policies (if supported)
			
			
Summary :
- Kubernetes Orchestration is automated process of maintaining the desired state of containerized applications by cordinating with control plane decissions
  and worked node execution across a cluster.
		
- API Server								:Acts as the central entry point of the cluster, handling all requests and communication between components.
- Scheduler									:Assigns newly created Pods to suitable worker nodes based on resource availability and constraints.
- Controller Manager						:Continuously monitors the cluster and ensures the actual state matches the desired state.
- etcd										:A distributed key-value store that permanently stores the cluster’s configuration and state.
- kubelet									:An agent that runs on each worker node and ensures Pods are running as instructed by the control plane.
- Container Runtime (Docker / containerd)	:Responsible for pulling images and creating, starting, and stopping containers.
- CNI (Container Network Interface)			:Provides networking for Pods, enabling pod-to-pod and pod-to-service communication.
- kube-proxy								:Manages network rules on the node to route traffic to the correct Pods and Services.
