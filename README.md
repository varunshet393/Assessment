## README.md

# Simple Invoice System

## Overview

This project implements a simple invoice management system with the following functionalities:

1. **Create Invoices**: Allows the creation of invoices with specified amounts and due dates.
2. **Pay Invoices**: Supports partial or full payment of invoices.
3. **Process Overdue Invoices**: Applies a late fee to overdue invoices and handles payments accordingly.

The system is designed with a REST API that can be easily extended to include durable persistence in the future.

## Features

- **Create Invoice**:
    - Endpoint: `POST /invoices`
    - Request Body:
      ```json
      {
        "amount": 199.99,
        "due_date": "2021-09-11"
      }
      ```
    - Response:
      ```json
      {
        "id": "1234"
      }
      ```
- **List All Invoices**:
    - Endpoint: `GET /invoices`
    - Response:
      ```json
      [
        {
          "id": "1234",
          "amount": 199.99,
          "paid_amount": 0,
          "due_date": "2021-09-11",
          "status": "pending"
        }
      ]
      ```
- **Pay Invoice**:
    - Endpoint: `POST /invoices/{id}/payments`
    - Request Body:
      ```json
      {
        "amount": 159.99
      }
      ```
    - Behavior:
        - If the payment covers the full amount, the invoice is marked as **paid**.
        - If the payment partially covers the amount, the remaining balance will still be pending.

- **Process Overdue Invoices**:
    - Endpoint: `POST /invoices/process-overdue`
    - Request Body:
      ```json
      {
        "late_fee": 10.5,
        "overdue_days": 10
      }
      ```
    - Behavior:
        - For fully unpaid overdue invoices:
            - The invoice is marked as **void**.
            - A new invoice is generated with the original amount plus the late fee and a new due date.
        - For partially paid overdue invoices:
            - The original invoice is marked as **paid**.
            - A new invoice is generated with the remaining amount plus the late fee and a new due date.

## Assumptions

- **Invoice Statuses**:
    - **pending**: The invoice is awaiting payment.
    - **paid**: The invoice has been fully paid.
    - **void**: The invoice was overdue and not paid, and has been replaced by a new invoice with additional late fees.

- **Invoice Handling**:
    - An invoice cannot be deleted once created.
    - Partial payments are allowed.
    - Once an invoice is marked as **paid** or **void**, it cannot be modified.



## Setup Instructions

### Prerequisites

- Docker
- Docker Compose

### Running the Application

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. Build and start the services using Docker Compose:
   ```bash
   docker-compose up --build
   ```

3. The API should now be accessible at `http://localhost:8080`.

### Running Unit Tests

- Unit tests are provided for the domain model. To run the tests:
  ```bash
  docker-compose run app VarunAssessmentV2
  ```

## Future Enhancements

- **Durable Persistence**: Currently, the system uses in-memory storage, but it can be easily extended to use a database such as PostgresSQL or MongoDB.
- **Authentication and Authorization**: Implementing user roles and permissions to secure the endpoints.



## Contact

For any queries or issues, please contact Varun Shetty at vrnshetty43@gmail.com.