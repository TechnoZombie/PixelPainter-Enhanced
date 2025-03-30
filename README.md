# PixelPainter: Enhanced!

A Java-based pixel art creation tool that combines simplicity with powerful features. PixelPainter: Enhanced! provides an intuitive grid-based canvas where users can create pixel art through a combination of keyboard controls and mouse interactions (work in progress).

This project is a continuation of the original PixelPainter project, with additional features and improvements. The main goal was to implement a GUI for the application, making it more user-friendly and accessible. It then grew large enough to be its own  project and repository.
The original can be found here:  https://github.com/TechnoZombie/PixelPainter .

It also makes use of a [custom graphics library](https://github.com/TechnoZombie/Simplegraphz), based on [simple-graphics](https://github.com/academia-de-codigo/simple-graphics) where custom methods to allow for JFrame manipulation were added, as well as Lombok and Slf4j.

## Project Overview

PixelPainter: Enhanced! is built with a focus on clean architecture and modular design. The application utilizes the SimpleGraphZ library for graphics rendering and implements a robust event-handling system for user interactions.

### Core Components

#### Canvas System
- **Grid-based Whiteboard** with customizable dimensions (default 800x400)
- **Cursor-based navigation** for precise pixel placement
- **Square-based rendering** with configurable pixel size (default 20px)
- Efficient rectangle-based rendering system

#### Color Management
- **10 predefined colors** accessible via number keys (1-0)
- **Custom color creation** with RGB input via dialog
- **Color Chooser dialog** for visual color selection
- **Color validation** with bounds checking (0-255 for RGB values)

#### File Operations
- **Save/Load functionality** with custom text-based format (.txt)
- **PNG export capability** with intelligent bounds detection
- **Screenshot functionality** for capturing the entire window
- Robust validation and confirmation dialogs for file operations

#### User Interface
- **Menu-driven interface** with File, Edit, Manager, and Help menus
- **Keyboard shortcuts** for all common operations
- **Confirmation dialogs** for destructive operations
- **Status messages** via logging system

### Key Features

- **Grid Navigation:** Move cursor with WASD keys for precise control
- **Color Selection:** Quick access to 10 colors via number keys
- **Canvas Operations:** Paint (Space), Erase (E), Clear (O)
- **File Management:** Save (Z), Load (X), Export PNG (C)
- **User Interface:** Menu-based access to all functionality
- **Custom Colors:** RGB input and visual color selector

### Control System

#### Keyboard Controls
- **Movement:** W (up), A (left), S (down), D (right)
- **Actions:** Space (paint), E (erase), O (clear canvas)
- **File Operations:** Z (save), X (load), C (export PNG)
- **Color Selection:** 1-0 keys for predefined colors
- 1: Black
- 2: Red
- 3: Green
- 4: Blue
- 5: Cyan
- 6: Yellow
- 7: Orange
- 8: Pink
- 9: Magenta
- 0: White

#### Menu Interface
- **File Menu:** Exit application
- **Edit Menu:** Clear canvas, Create custom color, Show color selector
- **Manager Menu:** Load file, Save file, Export PNG, Take screenshot
- **Help Menu:** View keybinds, Show image info (developer option)

### Dependencies

- **SimpleGraphZ Graphics Library** for rendering
- **Lombok** for reduced boilerplate
- **Java AWT** for advanced graphics operations
- **SLF4J** for logging
- **Swing** for dialog components

### Design Architecture

PixelPainter: Enhanced! uses a modular architecture with clear separation of concerns:

- **Whiteboard:** Main canvas control and grid generation
- **Cursor:** Visual indicator for current position
- **Movement:** Controls cursor navigation within boundaries
- **Coloring:** Handles paint, erase, and color management
- **KeyboardController:** Processes keyboard events
- **FileManager:** Handles save, load, and export operations
- **ConfirmationDialogs:** User interaction for important decisions
- **UserInterfaceManager:** Creates and manages menu system
- **ColorProcessor:** Converts between color representations
- **Messages:** Standardized user feedback

### Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/TechnoZombie/PixelPainter-Enhanced.git
   ```

2. Navigate to the project directory:
   ```bash
   cd PixelPainter-Enhanced
   ```

3. Compile the project:
   ```bash
   javac -cp ".:lib/*" tz/pixelpainter/Main.java
   ```

4. Run the application:
   ```bash
   java -cp ".:lib/*" tz.pixelpainter.Main
   ```

Note: The application requires the Simplegraphz custom graphics library, Lombok, and SLF4J. Make sure these dependencies are available in the lib directory.

### Future Development

Planned enhancements include:

- **Mouse interaction** for direct pixel manipulation
- **Drag painting** for continuous drawing (partially implemented)
- **Layer system** for complex artwork
- **Undo/redo functionality**
- **Enhanced color palette management**
- **Brush size options**
- **Additional export formats**

### Project Status

Currently in active development with regular updates and improvements. The core functionality is stable and fully operational.

PixelPainter: Enhanced! is a demonstration of modern Java application design, combining practical functionality with clean architecture and efficient implementation.
