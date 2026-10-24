# Linux Health & Disk Monitor

A colourful, responsive Linux monitoring dashboard built from scratch with Java 21, Spring Boot, OSHI, HTML, CSS and vanilla JavaScript. The app and UI are served from the same Spring Boot process on one AWS EC2 instance.

## Features

- CPU utilization and logical processor count
- RAM total, used, available and percentage
- Mounted filesystem usage, free space and capacity
- System uptime and 1/5/15-minute load averages
- HEALTHY / WARNING / CRITICAL summary
- Responsive dark dashboard with colourful metric cards
- Browser refresh every 60 seconds plus manual refresh
- REST endpoints at `/api/health` and `/api/ping`
- systemd deployment example

> This is a learning project. It is not a replacement for a production monitoring stack. The app currently has no login/authentication. Do not expose it publicly without adding authentication and HTTPS or restricting access to a trusted network.

## Architecture

Browser → Spring Boot REST API → OSHI → Linux host metrics, all on one EC2 instance.

## Project structure

```text
linux-health-monitor/
├── pom.xml
├── README.md
├── deploy/
│   └── linux-health-monitor.service
└── src/main/
    ├── java/com/pradeep/monitor/
    │   ├── LinuxHealthMonitorApplication.java
    │   ├── controller/MonitorController.java
    │   ├── model/DiskMetric.java
    │   ├── model/SystemHealth.java
    │   └── service/SystemMonitorService.java
    └── resources/
        ├── application.properties
        └── static/
            ├── index.html
            ├── styles.css
            └── app.js
```

## 1. Requirements

- Java 21 JDK
- Maven 3.6.3 or later
- Git (optional for local setup)
- An AWS account for EC2 deployment

Check your local tools:

```bash
java -version
mvn -version
git --version
```

## 2. Run locally

From the project root:

```bash
mvn clean package
java -jar target/linux-health-monitor.jar
```

Open `http://localhost:8080`.

Test the API in a second terminal:

```bash
curl http://localhost:8080/api/ping
curl http://localhost:8080/api/health
```

The first response should report `UP`; the second returns live metrics as JSON.

## 3. Launch an EC2 instance

For a learning environment:

1. Open AWS Console → EC2 → Launch instance.
2. Name it `linux-health-monitor`.
3. Select Amazon Linux 2023.
4. Choose a small instance type suitable for your account and budget.
5. Create/select a key pair and keep the private key secure.
6. Create a security group:
   - SSH TCP 22: **My IP only**.
   - Custom TCP 8080: ideally only your current public IP `/32` for testing.
7. Launch the instance and connect with EC2 Instance Connect or SSH.

Do not open SSH to `0.0.0.0/0`. Your public IP can change; if SSH times out, confirm your current public IP and update the inbound rule. Remove public port 8080 access when not using the dashboard.

## 4. Install Java and Maven on Amazon Linux 2023

Package availability can differ by repository. Check before installing:

```bash
sudo dnf update -y
sudo dnf search java-21
sudo dnf search maven
```

Install a Java 21 development kit and Maven package available in your enabled repositories. Common package names are:

```bash
sudo dnf install java-21-amazon-corretto-devel maven git -y
java -version
mvn -version
```

If Maven is not available in the enabled repositories, build the JAR on your local computer and upload it to EC2 instead. The runtime server only needs the compatible Java runtime.

## 5. Build locally and upload the JAR

On your computer, from the project root:

```bash
mvn clean package
```

The JAR should be `target/linux-health-monitor.jar`.

Upload it from Git Bash (replace the placeholders):

```bash
scp -i "/path/to/your-key.pem" target/linux-health-monitor.jar ec2-user@YOUR_EC2_PUBLIC_IP:/home/ec2-user/
```

SSH to the instance and confirm:

```bash
ssh -i "/path/to/your-key.pem" ec2-user@YOUR_EC2_PUBLIC_IP
ls -lh ~/linux-health-monitor.jar
```

If building on EC2 instead, copy/clone the full project there and run `mvn clean package`.

## 6. Run and test on EC2

```bash
java -jar ~/linux-health-monitor.jar
```

Keep the process running during the first test. In your browser, visit:

```text
http://YOUR_EC2_PUBLIC_IP:8080
```

Check the API from another SSH terminal:

```bash
curl -s http://localhost:8080/api/ping
curl -s http://localhost:8080/api/health
```

Stop the foreground process with `Ctrl+C` after testing.

## 7. Run as a systemd service

Create a dedicated application directory and user:

```bash
sudo useradd --system --home-dir /opt/linux-health-monitor --shell /sbin/nologin monitorapp
sudo mkdir -p /opt/linux-health-monitor
sudo cp /home/ec2-user/linux-health-monitor.jar /opt/linux-health-monitor/app.jar
sudo chown -R monitorapp:monitorapp /opt/linux-health-monitor
```

Copy the service file from this repository to `/etc/systemd/system/linux-health-monitor.service`, or create it manually using the included file as the template.

```bash
sudo systemctl daemon-reload
sudo systemctl enable --now linux-health-monitor
sudo systemctl status linux-health-monitor
```

Logs:

```bash
sudo journalctl -u linux-health-monitor -f
```

Restart after replacing the JAR:

```bash
sudo systemctl restart linux-health-monitor
```

## 8. Troubleshooting

### Browser cannot connect

```bash
sudo systemctl status linux-health-monitor
sudo journalctl -u linux-health-monitor --no-pager -n 100
sudo ss -lntp | grep 8080
curl -v http://localhost:8080/api/ping
```

Confirm the EC2 security group permits TCP 8080 from your current public IP. Also check network ACLs and host firewall rules if configured.

### Port 8080 is already in use

```bash
sudo ss -lntp | grep 8080
```

Stop the conflicting application or set another port with the `PORT` environment variable.

### Disk values do not match `df -h`

OSHI reports filesystem capacity through the operating system. Compare with:

```bash
df -h
free -h
uptime
top
```

Virtual filesystems and containerized environments can report different views. The application filters filesystems with zero total capacity.

### CPU briefly changes on refresh

CPU usage is sampled over a short interval, so it is expected to vary. A single metric sample is not a historical graph.

## 9. How the code works

1. `LinuxHealthMonitorApplication.java` starts Spring Boot.
2. `MonitorController.java` exposes `/api/health`.
3. `SystemMonitorService.java` uses OSHI to collect actual CPU, memory, disk and uptime data.
4. `SystemHealth.java` and `DiskMetric.java` define the JSON response structure.
5. `index.html` defines the dashboard sections.
6. `styles.css` provides the responsive dark UI and colourful cards.
7. `app.js` calls the API, renders the data and refreshes every 60 seconds.

## 10. Suggested next improvements

- Add login/authentication and HTTPS.
- Add time-series charts and persist metrics.
- Add alert notifications for high disk/RAM/CPU.
- Add GitHub Actions build workflow.
- Add CloudWatch integration.
- Put the app behind an ALB and TLS certificate.
- Add automated tests and a health endpoint.

## License

Use and modify for learning. Review dependencies and add a license before distributing a public fork.
