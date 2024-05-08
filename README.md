

# Mobile Applications for Sensing and Control (EEP 523) - Homework Assignment 2

## Project Overview

This project is a Kotlin-based mobile application developed as part of the EEP 523 course, aimed at exploring Kotlin's capabilities in accessing native features and APIs. The application features three main functionalities: accessing camera hardware to take and display photos, utilizing various sensors available on an Android device, and implementing a face-swap feature using face detection technologies.

## Features

### Bottom Toolbar
- **Tab 1 - Camera:** Allows users to switch between the front and back cameras, take photos, and save them locally.
- **Tab 2 - Sensors:** Displays data from three selected sensors (Magnetic, Proximity, Light).
- **Tab 3 - Swap Face:** Enables users to take two photos, detect faces, and swap them.

### Camera Tab
- Switch between front and back cameras.
- Capture and save photos locally.
- ImageView component to display the clicked photo.

### Sensor Tab
- Accesses Magnetic, Proximity, and Light sensors.
- Requests necessary permissions for hardware access.
- Displays error messages if a sensor is not available.

### Swap Face Tab
- Face detection to ensure photos contain faces before swapping.
- Swap faces between two images.
- Option to save the edited photo.

## Development

### Tools and Libraries
- **ML Kit for Face Detection:** Used for implementing the face-swap functionality.
- **Kotlin:** Primary programming language.
- **Android Studio:** Development environment.

### Design
- Optional Figma screens were designed to enhance the UI/UX, particularly for the sensor display.

## Challenges
The most challenging part of this assignment was implementing the face detection reliably and ensuring robust face swapping under various lighting conditions and angles.

## Acknowledgments
- Google ML Kit: https://developers.google.com/ml-kit/vision/face-detection
- OpenCV Face Swap Tutorial: https://learnopencv.com/face-swap-using-opencv-c-python/

## Time Invested
Approximately 20 hours were invested in developing, testing, and refining the application.

## Installation and Setup
Clone the repository from [GitHub](https://github.com/GaryFeng86/EEP523HW2.git), and open the project in Android Studio. Run the application on a compatible Android device or emulator.
## Watch Vedio
Google Drive(https://drive.google.com/file/d/13p7jycn5gYh4B903z5RyorcJiVE2hZ25/view?usp=sharing)
