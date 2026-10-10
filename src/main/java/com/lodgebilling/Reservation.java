package com.lodgebilling;

public class Reservation {

    private final String reservationId;
    private final String guestName;
    private final String phone;
    private final String roomType;
    private final String checkIn;
    private final String checkOut;
    private final int guests;
    private final String notes;

    public Reservation(
            String reservationId,
            String guestName,
            String phone,
            String roomType,
            String checkIn,
            String checkOut,
            int guests,
            String notes
    ) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.phone = phone;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guests = guests;
        this.notes = notes;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getPhone() {
        return phone;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public int getGuests() {
        return guests;
    }

    public String getNotes() {
        return notes;
    }
}
