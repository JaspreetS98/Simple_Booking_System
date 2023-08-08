package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
public class Booking { 
    private int customerID;
    private int flightID;
    private LocalDate bookingDate;
    private double bookingPrice;
	// Booking constructor
    public Booking( int customerID, int flightID, LocalDate bookingDate) throws FlightBookingSystemException {
    	new FlightBookingSystem();
    	this.customerID = customerID;
    	this.flightID = flightID;
    	this.bookingDate = bookingDate;
    	this.bookingPrice = FlightBookingSystem.getFlightByID(flightID).getTicketPrice();
    } 
    // implementation of Getter and Setter methods 
    public int getCustomerId() {
    	return customerID;
    }
    public int getFlightId() { 
    	return flightID;
    } 
    public double getBookingPrice() {
    	return bookingPrice;
    }
    public LocalDate getBookingDate() {
    	return bookingDate;
    }
    public void setCustomerId(int customerID) {
    	this.customerID = customerID;
    }
    public void setFlightId(int flightID) {
    	this.flightID = flightID;
    }
    public void setBookingPrice(double price) {
    	this.bookingPrice = price;
    }
    public void getDetailsShort() throws FlightBookingSystemException{
    	new FlightBookingSystem();
		Flight flight = FlightBookingSystem.getFlightByID(flightID);
    	System.out.println("*Booking date: "+bookingDate+" for "+flight.getDetailsShort());
    }      
}
