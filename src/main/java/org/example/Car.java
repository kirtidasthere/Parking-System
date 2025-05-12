package org.example; // Defines the package for organizing classes

import java.util.ArrayList;  // Used for dynamic list of parking spots
import java.util.List;       // Interface used for list abstraction

// Car class represents a car — Encapsulation & Abstraction
class Car {
    private String licenseplate; // Encapsulation — private variable hides data

    // Constructor to set license plate — Abstraction
    public Car(String licenseplate){
        this.licenseplate = licenseplate;
    }

    // Public method to access private data — Encapsulation
    public String getLicenseplate() {
        return licenseplate;
    }
}

// ParkingSpot class represents each parking space — Encapsulation & Abstraction
class ParkingSpot {
    private int spotNumber;     // Unique number for the parking spot
    private boolean available;  //  Availability status Indicates if the spot is free
    private Car car;            // Car object Car parked in this spot (null if empty)

    // Constructor to initialize the parking spot number Abstraction
    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.available = true;   // Spot is initially available
        this.car = null;         // No car is parked initially
    }

    // Getter for spot number Encapsulation
    public int getSpotNumber() {
        return spotNumber;
    }

    // Getter to check if the spot is available
    public boolean isAvailable() {
        return available;
    }

    // Getter to retrieve the car parked in the spot
    public Car getCar() {
        return car;
    }

    // Method to occupy a spot with a car Abstraction — hides how parking is done
    public void occupy(Car car) {
        this.car = car;        // Assign the car to this spot
        this.available = false; // Mark the spot as unavailable
    }

    // Method to vacate the spot (make it available again)
    // Vacates the parking spot — Abstraction
    public void vacate() {
        this.car = null;        // Remove the car
        this.available = true;  // Mark the spot as available
    }
}

// OOP Concept: Encapsulation, Abstraction, Composition (has-a ParkingSpot)
// ParkingLot class manages multiple parking spots — Modularity & Reusability
class ParkingLot {
    private List<ParkingSpot> spots; // List of parking spots in the lot is Composition

    // Constructor to initialize parking lot with a given capacity
    public ParkingLot(int capacity) {
        this.spots = new ArrayList<>();         // Create empty list
        for (int i = 0; i < capacity; i++) {
            spots.add(new ParkingSpot(i));      // Add parking spots with spot numbers
        }
    }

    // Method to park a car in the first available spot
    // Method to park car — Abstraction
    public boolean parkCar(Car car) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable()) {                            // Check if spot is available
                spot.occupy(car);                                // Occupy the spot with car
                System.out.println("Car with number : " +
                        car.getLicenseplate() + " parked at spot number :" + spot.getSpotNumber());
                return true;                                     // Exit after parking
            }
        }
        System.out.println("Sorry parking is full");             // If all spots are full
        return false;
    }

    // Method to remove a car based on its license plate
    //Method to remove car — Abstraction
    public boolean removeCar(String LicensePlate) {
        for (ParkingSpot spot : spots) {
            if (!spot.isAvailable() &&                           // Spot must be occupied
                    spot.getCar().getLicenseplate().equalsIgnoreCase(LicensePlate)) {
                spot.vacate();                                   // Vacate the spot
                System.out.println("Car with number : " +
                        LicensePlate + " removed from parking and spot number " + spot.getSpotNumber());
                return true;
            }
        }
        System.out.println("Car with number " + LicensePlate + " Not found"); // If not found
        return false;
    }
}

// OOP Concept: Object Creation, Abstraction, Reusability
// Main class to run the system — Modularity
class Test {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(5); // Create a parking lot with 5 spots

        // Create cars with different license plates
        // Creating Car objects — Object Instantiation
        Car car1 = new Car("MH298888");
        Car car2 = new Car("UP238181");
        Car car3 = new Car("MP241111");
        Car car4 = new Car("MP241187");
        Car car5 = new Car("MP241541");
        Car car6 = new Car("MP241167");

        // Park cars in the parking lot
        parkingLot.parkCar(car1); // Will park in spot 0
        parkingLot.parkCar(car2); // Will park in spot 1
        parkingLot.parkCar(car3); // Will park in spot 2
        parkingLot.parkCar(car4); // Will park in spot 3
        parkingLot.parkCar(car5); // Will park in spot 4

        // Remove car1 to free a spot
        parkingLot.removeCar("MH298888");

        // Try parking car6 in the now available spot
        parkingLot.parkCar(car6); // Will park in spot 0
    }
}


/*

Start
  |
  v
Create ParkingLot with specified capacity
  |
  v
Create Car objects (with license plates)
  |
  v
Park Car
  |
  v
[Is there an available spot?] ---> No ---> Print "Parking is full" ---> End
            |
           Yes
            |
            v
 Occupy Spot → Print "Car parked at spot X"
  |
  v
Remove Car
  |
  v
[Is car found by license plate?] ---> No ---> Print "Car not found" ---> End
            |
           Yes
            |
            v
Vacate Spot → Print "Car removed from spot X"
  |
  v
(Optional) Park another car
  |
  v
End

 */