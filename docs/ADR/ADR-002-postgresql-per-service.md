# ADR-002: Database Per Service

## Status

Accepted

## Context

Microservices should own their data.

## Decision

Use separate databases:

customerdb
accountdb
transactiondb
notificationdb

## Consequences

Pros

- Strong service boundaries
- Independent schema evolution

Cons

- Cross-service reporting complexity