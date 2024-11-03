# PixelPainter Enhanced

A Java-based pixel art creation tool that combines simplicity with powerful features. PixelPainter provides an intuitive grid-based canvas where users can create pixel art through a combination of keyboard controls and mouse interactions (work in progress).

This project  is a continuation of the original PixelPainter project, with additional features and improvements. The main goal was to implement a GUI  for the application, making it more user-friendly and accessible. It then grew large enough to be its own  project and repository.
The original can be found here:  https://github.com/TechnoZombie/PixelPainter .

It also makes use of a custom graphics library, based on simple-graphics [https://github.com/academia-de-codigo/simple-graphics](LINK) where custom methods to allow for JFram manipulation were added, as well as Lombok and Slf4j.

## Project Overview

PixelPainter is built with a focus on clean architecture and modular design. The application utilizes the SimpleGraphZ library for graphics rendering and implements a robust event-handling system for user interactions.

### Core Components

#### Canvas System
- **Grid-based drawing surface** with customizable dimensions
- **Dynamic cursor highlighting** for precise pixel placement
- **Efficient rectangle-based rendering** system
- Supports multiple canvas sizes and pixel densities

#### Color Management
- **Advanced color processing** system with RGB support
- **Custom color creation** and management
- **Color validation and conversion** utilities
- Preset color palette with 10 standard options

#### File Operations
- **Sophisticated file management** system
- Custom file format for saving pixel art (`.txt`)
- **PNG export capability** with intelligent bounds detection
- **Screenshot functionality** with automatic file naming

#### User Interface
- **Menu-driven interface** with keyboard shortcuts
- **Confirmation dialogs** for destructive operations
- **Color selector dialog** with RGB input
- **Status messages** and user feedback system

### Key Features

- **Event Handling:** Implements the Observer pattern for keyboard and mouse events
- **Color Processing:** Custom color handling system with RGB conversion capabilities
- **File Management:** Robust save/load system with error handling and validation
- **User Interface:** Clean, minimal interface focused on functionality

### Dependencies

- **Custom Simplegraphz Graphics Library**
- **Lombok** for reduced boilerplate
- **Java AWT** for advanced graphics operations
- **SLF4J** for logging

### Design Philosophy

PixelPainter was built with these principles in mind:

- **Modularity** and clean separation of concerns
- **Efficient graphics rendering**
- **Intuitive user interaction**
- **Robust error handling**
- **Extensible architecture**

The project demonstrates proper use of:

- Object-oriented design principles
- Event-driven programming
- File I/O operations
- Graphics programming concepts
- User interface design patterns

### Future Development

Planned enhancements include:

- **Layer system** for complex artwork
- **Undo/redo functionality**
- **Enhanced color palette management**
- **Brush size options**
- **Additional export formats**

### Project Status

Currently in active development with regular updates and improvements. The core functionality is stable and fully operational.

PixelPainter is a demonstration of modern Java application design, combining practical functionality with clean architecture and efficient implementation.
