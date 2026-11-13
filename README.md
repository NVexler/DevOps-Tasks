# DevOps-Tasks# DevOps Practical Assignment

## Overview

This repository contains the implementation of three DevOps tasks, demonstrating practical experience with CI/CD automation, configuration management, containerized applications, and Linux scripting.

The tasks cover:

1. **Jenkins CI/CD Pipeline** – Automated Java application build and artifact publishing using Groovy Shared Libraries.
2. **Ansible & Docker Compose** – Automated deployment of a containerized web application and database.
3. **Bash Extraction Script** – A reusable Linux utility for extracting compressed files, supporting multiple formats and command-line options.

---

# Notes:

## Webhook configuration and testing

Jenkins was running locally on the Ubuntu virtual machine, making it inaccessible to GitLab's external webhook service.

Therefore, an actual GitLab webhook could not be configured.

Instead, the webhook endpoint was tested manually using curl, simulating a webhook request with JSON payload containing commit information.

## Screenshots

Available at Screenshots directory.