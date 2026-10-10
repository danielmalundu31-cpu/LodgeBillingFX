package com.lodgebilling;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LodgeData {

    public static ObservableList<Bill> bills =
            FXCollections.observableArrayList();

    public static ObservableList<Reservation> reservations =
            FXCollections.observableArrayList();

    private static int billCounter = 4;
    private static int reservationCounter = 1;

    public static String nextBillNumber() {

        return String.format("B%03d", billCounter++);
    }

    public static String nextReservationNumber() {

        return String.format("R%03d", reservationCounter++);
    }

    public static double getRoomPrice(String roomType) {

        return switch (roomType) {

            case "Standard" -> 350.00;

            case "Deluxe" -> 550.00;

            case "Family" -> 750.00;

            default -> 0.00;
        };
    }
}
