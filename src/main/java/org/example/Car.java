package org.example;

import java.util.ArrayList;
import java.util.List;

class Car {
    private String licenseplate;
    public Car(String licenseplate){
        this.licenseplate=licenseplate;
    }

    public String getLicenseplate() {
        return licenseplate;
    }
}

class ParkingSpot
{
    private int spotNumber;
    private boolean available;
    private Car car;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.available = true;
        this.car = null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public Car getCar() {
        return car;
    }

    public void occupy(Car car)
    {
        this.car=car;
        this.available=false;

    }
    public void vacate()
    {
        this.car=null;
        this.available=true;

    }
}

class ParkingLot
{
    private List<ParkingSpot> spots;

    public ParkingLot(int capacity) {
        this.spots = new ArrayList<>();
        for (int i=0;i<capacity;i++)
        {
            spots.add(new ParkingSpot(i));
        }
    }

    public boolean parkCar(Car car)
    {
        for (ParkingSpot spot:spots)
        {
            if (spot.isAvailable())
            {
                spot.occupy(car);
                System.out.println("Car with number : "+ car.getLicenseplate() + " parked at spot number :"+spot.getSpotNumber());
                return true;
            }

        }
        System.out.println("Sorry parking is full");
        return false;

    }
    public boolean removeCar(String LicensePlate)
    {
        for (ParkingSpot spot:spots)
        {
            if (!spot.isAvailable() && spot.getCar().getLicenseplate().equalsIgnoreCase(LicensePlate))
            {
                spot.vacate();
                System.out.println("Car with number : "+LicensePlate+" removed from parking and spot number "+spot.getSpotNumber());
                return true;

            }
        }
        System.out.println("Car with number "+LicensePlate+" Not found");
        return false;

    }

}

class Test
{
    public static void main(String[] args) {
        ParkingLot parkingLot=new ParkingLot(5);
        Car car1=new Car("MH298888");
        Car car2=new Car("UP238181");
        Car car3=new Car("MP241111");
        Car car4=new Car("MP241187");
        Car car5=new Car("MP241541");
        Car car6=new Car("MP241167");

        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);
        parkingLot.parkCar(car4);
        parkingLot.parkCar(car5);

        //parkingLot.removeCar("MH298888");
        parkingLot.parkCar(car6);

    }
}