# Dependency Inversion Principle — Gradle Multi-Module PoC

A proof-of-concept Kotlin project demonstrating the **Dependency Inversion Principle (DIP)** applied to a Gradle multi-module architecture.

> Companion article: [Dependency Inversion Principle on Gradle Multi-Module Project](https://medium.com/@lolegrand/dependency-inversion-principle-on-gradle-multi-module-project-36a55eb17ed2)

---

## What this project demonstrates

The DIP states:

- High-level modules should not depend on low-level modules. Both should depend on abstractions.
- Abstractions should not depend on details. Details should depend on abstractions.

In a Gradle multi-module project this means the application module never imports a concrete implementation — it only knows the abstraction defined in `:library:api`. The actual implementation is resolved at **build time** via a Gradle property, keeping all modules cleanly decoupled.

---

## Project structure

```
.
├── application/          # Entry point — depends only on :library:api
└── library/
    ├── api/              # Abstraction layer (interface)
    ├── prod/             # Production implementation
    ├── dev/              # Development implementation
    └── demo/             # Demo implementation
```

### Module dependency graph

```
:application ──► :library:api ◄── :library:prod
                              ◄── :library:dev
                              ◄── :library:demo
```

`:application` depends on `:library:api` at compile time. One implementation module is added at build time based on the `buildVariant` property.

---

## Getting started

**Requirements**: JDK 11+, no other dependencies.

### Build

```bash
# Default — dev variant
./gradlew build

# Production variant
./gradlew build -PbuildVariant=prod

# Demo variant
./gradlew build -PbuildVariant=demo
```

### Run

```bash
./gradlew :application:run

# With a specific variant
./gradlew :application:run -PbuildVariant=prod
```

---

## How it works

`Main.kt` only interacts with the `MyApplicationRepository` interface from `:library:api`:

```kotlin
fun main() {
    val myRepository = MyApplicationRepoProvider().getMyApplicationRepo()
    println(myRepository.getMyApplicationString())
}
```

Each implementation module (prod / dev / demo) ships its own `MyApplicationRepoProvider` that returns the appropriate concrete class. The application never references any of them directly — the right one is wired in by Gradle's dependency resolution.

| Variant | Output |
|---------|--------|
| `dev`   | `Hello World from Dev !!!` |
| `prod`  | `Hello World from Prod !!!` |
| `demo`  | `Hello world from Demo !!!` |

---

## Tech stack

| | |
|---|---|
| Language | Kotlin 2.1.10 |
| Build system | Gradle 8.10 (Kotlin DSL) |
| Target | JVM |
