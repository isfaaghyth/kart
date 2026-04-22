# Kart - Project Overview & Agent Knowledge

## Overall Purpose
Kart is a native Android digital wallet application designed to store, track, and manage user memberships and subscriptions. It allows users to digitize physical cards (like Starbucks or IKEA) and track recurring digital subscriptions (like Netflix or AWS) in one unified interface. By preloading a catalog of popular brands, the app automatically styles added cards with the correct brand colors and favicons, providing a visually appealing and organized way to access card IDs or scannable barcodes.

## Tech Stack
The project relies entirely on a modern Android development stack:
- **Language:** Kotlin (v2.0.21)
- **UI Toolkit:** Jetpack Compose, leveraging Material 3 components and the Androidx Navigation 3 library for routing.
- **Architecture & Concurrency:** Android ViewModels paired with Kotlin Coroutines and Flows (`StateFlow`) to enforce a reactive, unidirectional data flow. The UI layer implements a hybrid **MVVM + MVI** pattern (as seen in the `home` feature): the ViewModel and Screen interact in a single streamline through states, and actions/events are invoked via a single entry point. *(Note: side-effects are planned but not yet fully implemented).*
- **Local Storage:** Room Database (using KSP for annotation processing) to persist card data locally.
- **Serialization:** `kotlinx-serialization` for processing bundled JSON assets.
- **Image Loading:** Coil (`coil-compose`) to fetch and cache remote images like brand favicons.
- **Dependency Injection:** A lightweight, manual DI pattern managed via a central `DIModuleManager` and an application-level interface, deliberately avoiding heavyweight frameworks like Hilt or Koin.
- **Build System:** Gradle using Kotlin DSL (`build.gradle.kts`) and Version Catalogs (`libs.versions.toml`) for dependency management.

## Main Features
- **Digital Card Wallet:** Allows users to view all their saved cards in a clean feed, displaying visually distinct cards based on the brand.
- **Membership vs. Subscription Tracking:** Differentiates between standard membership cards (requiring only an ID/barcode) and time-bound subscriptions (tracking renewal cycles like Daily, Monthly, Annual and their specific expiration dates).
- **Brand Catalog Integration:** Uses a bundled `brand_list.json` to populate a searchable dropdown when adding new cards, automatically applying the brand's exact colors, slug, category, and logo to the card.
- **Search and Categorization:** Features a dynamic home screen that allows users to filter their cards in real-time by keyword or by category chips (e.g., Coffee, Groceries, Cloud, Utilities).
- **Detailed Card View:** Provides a dedicated screen for individual cards, rendering either a physical-style card or a scannable barcode layout depending on the card type.

## Project Architecture
The repository is heavily modularized by feature and layer to enforce strict separation of concerns:
- **`app` Module:** The main application entry point that wires up the dependency injection, initializes the database, and hosts the root Compose navigation graph.
- **`features` Modules:** Isolated UI modules containing Jetpack Compose screens, ViewModels, and UI States. This includes `home` for the main dashboard, `detail` for the card detail view, and `input` for the bottom sheet used to add new cards.
- **`data` Modules:** Split into `api` (interfaces/models) and `impl` (implementations) to ensure the UI layer only depends on abstractions. Contains the `local` layer for Room database/DAOs and the `repository` layer acting as the domain layer.
- **`libraries` Modules:** Shared infrastructural components used across the app, such as `design-system` for custom Compose UI components, `navigation` for custom routing logic, and `i18n` for string resources and localization providers.
