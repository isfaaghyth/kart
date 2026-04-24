---
name: kmp-module-migrator
description: Migrates Android-only library modules (API or Implementation) to Kotlin Multiplatform (KMP). Handles Room KMP setup including plugins, schema configuration, and multi-target KSP dependencies.
---

# KMP Module Migrator

This skill guides the migration of an Android-only library module to a Kotlin Multiplatform (KMP) module.

## Workflow

### 1. Update `build.gradle.kts`

Replace the standard Android Library setup with KMP configuration.

**Plugins Block:**
```kotlin
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.devtools.ksp)
    // For implementation modules with Room:
    alias(libs.plugins.androidx.room) 
    // Keep other plugins like serialization if present
}
```

**Room Configuration (for Impl modules):**
```kotlin
room {
    schemaDirectory("$projectDir/schemas")
}
```

**Kotlin Configuration:**
Add the `kotlin` block and move `android` settings into `androidLibrary`.

```kotlin
kotlin {
    androidLibrary {
        namespace = "app.isfa.kart.<module-package>"
        compileSdk {
            version = release(36) { minorApiLevel = 1 }
        }
        minSdk = 24
    }

    val xcfName = "<ModuleName>" // e.g., DbApi or DbImpl

    iosX64 { binaries.framework { baseName = xcfName } }
    iosArm64 { binaries.framework { baseName = xcfName } }
    iosSimulatorArm64 { binaries.framework { baseName = xcfName } }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                // Move your dependencies here
                // For Room Impl:
                implementation(libs.androidx.room.runtime)
                implementation(libs.androidx.sqlite.bundled)
            }
        }
    }
}
```

**KSP Dependencies (for Impl modules):**
Add KSP for each target since KMP KSP is target-aware.
```kotlin
dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}
```

### 2. Map Dependencies

Convert Android-specific dependencies to their KMP equivalents in `commonMain`:

| Android Library | KMP Equivalent (commonMain) |
|-----------------|-----------------------------|
| `libs.androidx.room.common` | `libs.androidx.room.kmp.common` |
| `libs.androidx.room.runtime` | `libs.androidx.room.runtime` (ensure KMP version used) |
| `N/A` (Room Impl) | `libs.androidx.sqlite.bundled` (required for KMP Room) |
| `libs.kotlinx.serialization.core` | `libs.kotlinx.serialization.core` |
| `libs.kotlinx.coroutines` | `libs.kotlinx.coroutines` |

### 3. File Reorganization

Move all source files from the Android-specific `main` source set to the KMP `commonMain` source set.

```bash
# Example
mkdir -p <module>/src/commonMain/kotlin
mv <module>/src/main/kotlin/* <module>/src/commonMain/kotlin/
rm -rf <module>/src/main
```

### 4. Verification

Verify the migration by building the module.

```bash
./gradlew :<module-path>:assemble
```

For a more rigorous check of Kotlin Native (iOS):
```bash
./gradlew :<module-path>:compileKotlinIosArm64
```
