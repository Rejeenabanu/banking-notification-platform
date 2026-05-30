# ADR-003: Redis Streams

## Status

Accepted

## Context

Need asynchronous communication.

## Decision

Use Redis Streams.

Stream Name:

bank-events

## Consequences

Pros

- Lightweight
- Fast
- Easy local setup

Cons

- Not as feature rich as Kafka