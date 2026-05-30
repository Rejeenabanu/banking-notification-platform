# ADR-001: Microservices Architecture

## Status

Accepted

## Context

The banking platform requires:

- Independent deployment
- Scalability
- Domain isolation

## Decision

Use microservices:

- customer-service
- account-service
- transaction-service
- notification-service

## Consequences

Pros:

- Independent scaling
- Team autonomy
- Domain ownership

Cons:

- Increased complexity
- Service communication overhead