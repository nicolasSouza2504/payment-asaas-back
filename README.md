# README.md (English Version)

## Payment Integration with Asaas - Backend Application

This project is a backend application that provides webhook integration with the **Asaas** payment platform. It allows users to register, generate API keys, and manage payments through secure APIs.

**GitHub Repository**: [https://github.com/nicolasSouza2504/payment-asaas-back](https://github.com/nicolasSouza2504/payment-asaas-back)

---

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [API Endpoints](#api-endpoints)
  - [1. User Registration](#1-user-registration)
  - [2. User Login](#2-user-login)
  - [3. List All Payments](#3-list-all-payments)
- [Configuring Asaas Webhooks](#configuring-asaas-webhooks)
- [Usage](#usage)
- [Technologies Used](#technologies-used)
- [License](#license)

---

## Features

- **User Registration**: Create users and generate unique API keys linked with your Asaas API token.
- **Webhook Configuration**: Utilize the generated API key to configure Asaas webhooks, ensuring payment updates are linked to the correct user.
- **Authentication**: Secure login mechanism that returns an `authToken` for authenticated requests.
- **Payment Retrieval**: Access all payment updates from your Asaas account through a dedicated API endpoint.

## Architecture

The application is hosted on Amazon Web Services (AWS):

- **Amazon EC2**: Hosts the backend application.
- **Amazon RDS (PostgreSQL)**: Manages the relational database.

---

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven** or **Gradle**
- **Asaas API Access**: You need access your asaas account and parameterize the webhook for payment updates.

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/nicolasSouza2504/payment-asaas-back.git
