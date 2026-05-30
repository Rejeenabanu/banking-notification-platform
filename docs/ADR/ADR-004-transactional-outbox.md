# ADR-004: Transactional Outbox Pattern

## Status

Accepted

## Context

Events must never be lost.

Problem:

Transaction Saved
Event Publish Failed

Causes data inconsistency.

## Decision

Use Transactional Outbox Pattern.

Transaction
Outbox Event

saved in same DB transaction.

Scheduler publishes later.

## Consequences

Pros

- Reliable event delivery
- Event replay support

Cons

- Additional table
- Scheduler complexity