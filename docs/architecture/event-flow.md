# Event Flow Diagram

```mermaid
sequenceDiagram

    participant User
    participant TransactionService
    participant AccountService
    participant PostgreSQL
    participant Outbox
    participant Redis
    participant NotificationService
    participant Email

    User->>TransactionService: Deposit ₹5000

    TransactionService->>AccountService: Update Balance

    AccountService-->>TransactionService: Success

    TransactionService->>PostgreSQL: Save Transaction

    TransactionService->>Outbox: Save Event

    Outbox->>Redis: Publish Event

    Redis->>NotificationService: Consume Event

    NotificationService->>PostgreSQL: Save Notification

    NotificationService->>Email: Send Email

    Email-->>User: Transaction Alert
```