package com.lodgebilling;

public class Bill {

    private final String billNumber;
    private final String guest;
    private final String room;
    private final int nights;
    private final double total;
    private final double paid;

    public Bill(
            String billNumber,
            String guest,
            String room,
            int nights,
            double total,
            double paid
    ) {
        this.billNumber = billNumber;
        this.guest = guest;
        this.room = room;
        this.nights = nights;
        this.total = total;
        this.paid = paid;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public String getGuest() {
        return guest;
    }

    public String getRoom() {
        return room;
    }

    public int getNights() {
        return nights;
    }

    public double getTotal() {
        return total;
    }

    public double getPaid() {
        return paid;
    }

    public double getBalance() {
        return total - paid;
    }
}
