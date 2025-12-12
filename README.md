Simple Flight Reservation System

Author: Pavan Kumar Avala

Overview:

	This is a simple console-based Java application that simulates a basic Flight Reservation System using only core Java.

	The application allows users to:

		Search for flights by destination and date

		Book a flight if seats are available

		View reservations for a customer

		All data is stored in memory, and no external libraries or databases are used.

Tech Stack:

	Java 8 or later

	JUnit 5 (only if you want to run tests manually)

	No external dependencies

Project Structure:

flight-reservation-system/
│
└── src/
    ├── main/java/
    │        Flight.java
    │        Reservation.java
    │        FlightService.java
    │        Main.java
    │
    └── test/java/
             FlightServiceTest.java


If you do not want to use JUnit, simply remove the test folder.

How to Run the Application:

	1. Open the project in your IDE

		You can use:

			IntelliJ IDEA

			Eclipse

			VS Code

			NetBeans

	2. Make sure your JDK is configured

		Use Java 8+.

	3. Run the application

		Open the file:

			src/main/java/Main.java

			Right-click → Run Main

		You will see the menu:

			1. Search flights
			2. Book a flight
			3. View my reservations
			4. Exit

Running Tests:

	If you want to run tests without Maven:

		Option 1 — Run inside IDE

			Create a JUnit run configuration

			Add the JUnit 5 JAR libraries manually

			Run FlightServiceTest.java

		Option 2 — Skip tests entirely

Design Explanation:

	Flight.java

		Represents flight information:

			Flight number

			Destination

			Departure time

			Available seats

	Reservation.java

		Represents a customer booking:

			Customer name

			The flight booked

			Number of seats

	FlightService.java

		Contains all business logic, including:

		Searching available flights

		Validating seat availability

		Booking reservations

		Updating seat counts

		Returning customer reservation history

	Main.java

		A console-based UI to interact with the system:

			Reads user input

			Connects with FlightService

			Displays output

Design Considerations:

	What this project focuses on:

		Object-oriented design

		Clean separation of models, service, UI

		Simple logic and readability

		Basic validation and error handling

	What a real-world system would include:

		Database storage (MySQL, PostgreSQL, MongoDB, etc.)

		REST APIs

		Authentication

		Logging & monitoring

		Handling concurrency (multiple users booking at same time)

		Better UI (web or mobile)

		This project intentionally stays simple to showcase Java fundamentals.

Limitations:

	Data resets every time program restarts

	No concurrency control

	Hardcoded sample flights

	Console-only user interface

	Not built for production (only for assessment demonstration)
	