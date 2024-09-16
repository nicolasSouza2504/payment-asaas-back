

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


2. **Navigate to the Project Directory**

   ```bash
   cd payment-asaas-back

3. **Configure Application Properties**

   Update the application.properties or application.yml file with your database credentials and Asaas API configurations.

## API Endpoints

### 1. User Registration

- **Endpoint**: `/api/register`
- **Method**: `POST`
- **Description**: Registers a new user and generates an API key.
- **Request Body**:

  ```json
  {
    "username": "your_username",
    "password": "your_password",
    "apiKeyPayments": "your_asaas_api_token"
  }

### 2. User Login

- **Endpoint**: `/api/login`
- **Method**: `POST`
- **Description**: Authenticates the user and returns an `authToken`.
- **Request Body**:

  ```json
  {
    "username": "your_username",
    "password": "your_password"
  }

  - **Response**:

  ```json
  {
    "authToken": "your_auth_token"
  }


### 3. List All Payments

- **Endpoint**: `/api/payments/list-all-payments`
- **Method**: `GET`
- **Description**: Retrieves all payment updates linked to your Asaas account.
- **Headers**:

  - `Cookie`: `authToken=your_auth_token`

- **Response**:

  ```json
  [
    {
      "paymentId": "pay_12345",
      "status": "PENDING",
      "amount": 100.0,
      "date": "2024-09-15"
    },
    {
      "paymentId": "pay_67890",
      "status": "COMPLETED",
      "amount": 50.0,
      "date": "2024-09-16"
    }
  ]

  ## Configuring Asaas Webhooks

When setting up webhooks in your Asaas account, use the generated `apiKey` as a parameter in the webhook URL. This ensures that payment updates are correctly associated with your user account.

**Example Webhook URL**:

https://yourdomain.com/webhook?apiKey=your_generated_api_key

## Usage

1. **Register a User**: Use the registration API to create a new user and obtain an `apiKey`.

2. **Configure Asaas Webhooks**: Set up webhooks in your Asaas account using the `apiKey`.

3. **Login**: Authenticate using the login API to receive an `authToken`.

4. **Access Payment Data**: Use the `authToken` as a cookie to access the `/api/payments/list-all-payments` endpoint.

## Technologies Used

- **Java**
- **Quarkus**
- **PostgreSQL**
- **AWS EC2**
- **AWS RDS**

