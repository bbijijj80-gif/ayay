# IntelliJ IDEA Settings for Camera Mod

## Importing the Project

1. Open IntelliJ IDEA
2. Click "Open" and select the `/workspace` folder
3. Wait for Gradle to sync (this may take a few minutes on first import)

## Setup Instructions

### Prerequisites
- Java JDK 8 (required for Minecraft 1.12.2)
- IntelliJ IDEA (Community or Ultimate edition)

### First Time Setup

1. **Set JDK**: 
   - Go to File → Project Structure → SDKs
   - Add JDK 1.8 if not already present

2. **Run Configuration**:
   - After Gradle sync completes, run configurations will be auto-generated
   - Look for "runClient" and "runServer" in Gradle tasks

3. **Building the Mod**:
   - Run `gradle build` command
   - The compiled JAR will be in `build/libs/`

## Troubleshooting

### If you get download errors:
1. Check your internet connection
2. Try running with offline mode disabled
3. Clear Gradle cache: `gradle --refresh-dependencies`

### Common Issues:

**"Could not resolve artifacts"**
- This is usually a network timeout issue
- Try running: `gradle build --offline` after first successful sync
- Or increase timeout in gradle.properties

**"Java version mismatch"**
- Ensure you're using Java 8
- Check: File → Project Structure → Project → Project SDK

## Running the Mod

1. In Gradle panel, expand `Tasks` → `forgegradle`
2. Double-click `runClient` to start Minecraft with the mod
3. Look for camera and monitor blocks in creative tab (Redstone category)

## Usage In-Game

1. Place a Camera block anywhere
2. Place a Monitor block anywhere (can be far away)
3. Right-click Camera to set an ID (e.g., "cam1")
4. Right-click Monitor and enter the same ID to connect
5. Monitor will display "Signal: OK" when connected
