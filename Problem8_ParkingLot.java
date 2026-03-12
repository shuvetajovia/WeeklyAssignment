import java.util.*;

public class Problem8_ParkingLot {

    private class Spot {
        String licensePlate;
        boolean occupied;
    }

    private Spot[] parkingSpots;
    private int capacity;

    public Problem8_ParkingLot(int capacity) {
        this.capacity = capacity;
        parkingSpots = new Spot[capacity];
        for (int i = 0; i < capacity; i++) parkingSpots[i] = new Spot();
    }

    public int parkVehicle(String licensePlate) {
        int hash = Math.abs(licensePlate.hashCode() % capacity);
        int probes = 0;
        while (parkingSpots[hash].occupied) {
            hash = (hash + 1) % capacity;
            probes++;
        }
        parkingSpots[hash].licensePlate = licensePlate;
        parkingSpots[hash].occupied = true;
        return hash; // spot assigned
    }

    public void exitVehicle(String licensePlate) {
        for (Spot spot : parkingSpots) {
            if (spot.occupied && licensePlate.equals(spot.licensePlate)) {
                spot.occupied = false;
                spot.licensePlate = null;
                break;
            }
        }
    }
}