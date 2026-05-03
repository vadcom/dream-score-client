# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Java Maven library (JDK 20) that provides an HTTP client for the DreamScore leaderboard service at `http://dreamscore.sigma5.link:8080/score/v3`.

## Build & Test Commands

```bash
# Build and install to local Maven repo
mvn install

# Compile only
mvn compile

# Run tests (currently @Disabled — require a running DreamScore server)
mvn test

# Package JAR without installing
mvn package
```

To run tests against a remote server, set the `profile` environment variable to `"remote"` before running `mvn test`. Without it, tests point to `http://localhost:8080/score/v3`.

## Architecture

Two classes in `src/main/java/link/sigma5/dreamscore/client/`:

**`ScoreClient`** — the main API client. Constructed with `applicationId`, `deviceId`, and an optional custom base URL. Uses Java 11's built-in `HttpClient` (5-second timeout) to talk to the REST API.

- `pushScore(sectionId, localScore, name, score, levels)` — POST to `/api/{applicationId}/{sectionId}`. Appends `?deviceId=` only when `localScore` is `true` (device-scoped leaderboard).
- `pullScore(sectionId, localScore, page, recordInPage)` — GET with `positionToSkip` and `count` query params. Same `deviceId` scoping logic.
- Both methods return `List<Score>` parsed via Jackson `ObjectMapper`.

**`Score`** — Jackson-annotated POJO (fields: `id`, `position`, `score`, `name`, `date`, `subscription`, `app`, `section`, `selected`, `deviceId`, `levels`). Uses a fluent setter pattern (setters return `this`).

## Key Conventions

- **Error handling** is minimal: any non-200 HTTP response throws `RuntimeException`. No custom exception types exist.
- **URL construction** uses manual string concatenation + `URLEncoder` — not a URI builder library.
- **No logging framework** is configured.
- The library is published under `groupId: link.sigma5`, `artifactId: dream-score-client`.
