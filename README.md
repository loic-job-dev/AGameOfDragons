# AGameOfDragons
Project created to learn Java basics, specially OOP

## Prerequisites
- [Java JDK 17] (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or higher
- An IDE like IntelliJ
- The library JavaFX 17.0.16 (https://gluonhq.com/products/javafx/)

## Configuration
- In the project, go to File > Project Structure > Libraries > + > Java.
- Add the path to javafx-sdk-17/lib
- Then create a new configuration in Run/Debug Configurations,and add to VM options :
``--module-path "path/to/javafx-sdk-17/lib" --add-modules javafx.controls,javafx.fxml``

## Installation
Clone the repository :

``git clone git@github.com:loic-job-dev/AGameOfDragons.git``

``cd AGameOfDragons``