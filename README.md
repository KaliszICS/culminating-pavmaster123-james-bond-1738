# Super Kalisz World

A fun and engaging platformer game created by Pavarasan Karunainathan, Levon Alexanian, and Wayne Bai.

## Description

Super Kalisz World is a 2D platformer game featuring Kalisz Junior (the white cube).

## Features

- **Multiple Levels**: Progress through different challenging levels
- **Customizable Controls**: 
  - Configurable jump, left, and right movement keys
  - Easy-to-use key binding interface
- **User-Friendly Interface**:
  - Nature-themed main menu
  - Intuitive level selection screen
  - Easy-to-navigate settings menu
  - Credits screen

## Controls
Default controls:
- **W**: Jump
- **A**: Move Left
- **D**: Move Right
- **Esc**: Leave the Level

Controls can be customized in the Settings menu.

## Compilation
Run in the folder outside ./src:
```
javac ./src/game/*.java ./src/game/level/*.java ./src/game/menu/*.java -d ./
jar -cfe Game.jar game.Game ./game/*
java -jar Game.jar
```

## Installation

1. Ensure you have Java installed on your system
2. Download the latest release from the releases page
3. Open the folder in terminal
4. Run "java -jar Game.jar"

## Credits

### Development Team
- Wayne Bai
- Levon Alexanian
- Pavarasan Karunainathan

## License

© 2025 All Rights Reserved

