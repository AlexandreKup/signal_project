# Cardio Data Simulator

The Cardio Data Simulator is a program that runs on Java. It is used to generate cardiovascular data for many patients. This tool is really helpful when we are learning. It lets students work with real time data from things like ECG and blood pressure. They can also see things like blood saturation and other signals that have to do with the heart.

## Features

- Simulate real-time ECG, blood pressure, blood saturation, and blood levels data.
- Supports multiple output strategies:
  - Console output for direct observation.
  - File output for data persistence.
  - WebSocket and TCP output for networked data streaming.
- Configurable patient count and data generation rate.
- Randomized patient ID assignment for simulated data diversity.

## Design Patterns (Part 4)

The architecture utilizes several behavioral and creational design patterns to ensure modularity:

- Singleton: Ensures global state management for the `DataStorage` and `HealthDataSimulator`.
- Strategy: Decouples specific health metric analysis logic (ECG, BP, Oxygen) from the generator.
- Factory Method: Handles the type-safe creation of specialized medical alerts.
- Decorator: Allows for the dynamic extension of alerts with priority and repetition logic.

## Getting Started

### Prerequisites

- Java JDK 11 or newer.
- Maven for managing dependencies and compiling the application.

### Installation

1. Clone the repository:

   ```sh
   git clone https://github.com/tpepels/signal_project.git
   ```

2. Navigate to the project directory:

   ```sh
   cd signal_project
   ```

3. Compile and package the application using Maven:
   ```sh
   mvn clean package
   ```
   This step compiles the source code and creates the project JAR located in the `target/` directory.

### Running the Simulator

The simulator should be started using Maven.

Default execution:

```sh
mvn exec:java "-Dexec.mainClass=com.cardio_generator.HealthDataSimulator"
```

To run with specific options (e.g., to set the patient count and choose an output strategy):

```sh
mvn exec:java "-Dexec.mainClass=com.cardio_generator.HealthDataSimulator" "-Dexec.args=--patient-count 100 --output file:./output"
```

### Supported Output Options

- `console`: Directly prints the simulated data to the console.
- `file:<directory>`: Saves the simulated data to files within the specified directory.
- `websocket:<port>`: Streams the simulated data to WebSocket clients connected to the specified port.
- `tcp:<port>`: Streams the simulated data to TCP clients connected to the specified port.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Project Members
- Student ID: i6372447
- Student ID: i6436806

## UML Models

The UML class diagrams for Part 2 are located in the [`uml_models`](./uml_models) directory.
They include:
- Alert Generation System
- Data Storage System
- Patient Identification System
- Data Access Layer

The directory also includes `Rationale.txt`, which explains the design choices and responsibilities of all the subsystems.

## Testing

This project uses JUnit 5 for unit testing.

To run all tests:

```bash
mvn clean test
```

To generate the coverage report:

```bash
mvn package
```

The JaCoCo report is generated in:

```text
target/site/jacoco/index.html
```

Current tests include:
- AlertGenerator
- Alert
- DataStorage
- FileDataReader
- Patient
- PatientRecord
- AlertFactory
- Strategy classes
- WebSocketDataReader
- Singleton implementation

Current result:

```text
22 tests passed
0 failures
BUILD SUCCESS
```

## Part 3 Screenshots

### Test Results

```text
screenshots/Part 3/
```

## Part 4 Screenshots

Screenshots are available in:

```text
screenshots/Part 4/
```

## Part 5 - Real-Time WebSocket Data Reader

A WebSocket client was added for Part 5.

Implemented classes:

- `WebSocketDataReader`
- `WebSocketReaderMain`

Features:

- Connects to a WebSocket server
- Receives live patient data
- Parses incoming WebSocket messages
- Stores valid patient records in `DataStorage`
- Ignores invalid or corrupted messages to prevent crashes

Expected WebSocket message format:

```text
patientId,timestamp,label,data
```

Examples:

```text
1,1779200135840,HeartRate,85
1,1779200135840,Saturation,93.0%
```

### Real-Time Integration Test

The WebSocket implementation was tested successfully with the live simulator.

Start simulator:

```bash
mvn exec:java "-Dexec.mainClass=com.cardio_generator.HealthDataSimulator" "-Dexec.args=--output websocket:8080"
```

Start WebSocket reader in a second terminal:

```bash
mvn exec:java "-Dexec.mainClass=com.data_management.WebSocketReaderMain"
```

Live patient records were received and stored successfully.

## Part 5 Screenshots

Screenshots are available in:

```text
screenshots/Part 5/
```

Including:

- Live WebSocket connection
- Real-time patient data reception
- Test validation