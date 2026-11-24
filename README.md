# RNHD (No Hit Delay)

A lightweight **No Hit Delay** mod (`rnhd`) for **Minecraft 1.8.9 (Ornithe / OneClient)** with native **OneConfig v1** support.

IT IS CALLED RNHD IN ONECONFIG

Restores Minecraft 1.7-style attack responsiveness by removing or customizing the 10-tick miss attack cooldown (`Minecraft#doAttack`) introduced in 1.8, while leaving the GUI anti-click-through cooldown untouched.

## Features

- **Enable / Disable Toggle** — Quickly switch between custom hit delay and vanilla 1.8.9 behavior (10-tick miss cooldown).
- **Configurable Hit Delay Slider (`0`–`10` ticks)** — Default `0` ticks (classic 1.7 no hit delay), adjustable in 1-tick increments up to `10` ticks (vanilla).
- **OneConfig v1 Integration** — Configured in-game under **Combat → RNHD** (persisted to `rnhd.toml`). Uses reflection so the mod still loads cleanly with defaults if OneConfig is absent.

## Installation

1. Ensure you are running **Minecraft 1.8.9** with **Fabric Loader ≥ 0.15.0** (e.g. OneClient `1.8.9 Fabric` / Ornithe).
2. Download or build `rnhd-<version>+mc1.8.9.jar`.
3. Place the jar into your `mods` folder (`%APPDATA%\Polyfrost\OneClient\clusters\1.8.9 Fabric\mods` for OneClient).
4. **OneConfig v1** (`≥ 1.6.0`) is recommended for in-game configuration.

## Building from Source

Requires **JDK 17 or 21** on your `PATH` (compiles Java 8 bytecode for Minecraft 1.8.9):

```powershell
.\build.ps1
```

`build.ps1` automatically bootstraps Gradle `8.12.1` (if not already cached), builds the remapped jar into `build/libs/`, and copies it to OneClient's `1.8.9 Fabric\mods` directory if present.

## License

[MIT](LICENSE)
