package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.commands.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Customer { 
    private int id;
    private String name;
    private String phone;
    private String email;
    private final List<Booking> bookings;
    private Boolean isDeleted;
	// Customer constructor
    public Customer (int id,String name, String phone, String email, Boolean isDeleted) {
    	this.id = id;
    	this.name = name;
    	this.phone = phone;
    	this.email = email;	
    	bookings = new ArrayList<>();
    	this.isDeleted = isDeleted;
    }
    // TODO: implementation of Getter and Setter methods
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public Boolean getisDeleted() {
        return isDeleted;
    }
    public void setisDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDetailsShort(){
    	return "Customer #" + id + " - " + name;
    }
    public void getDetailsLong() throws FlightBookingSystemException {
    	if (getisDeleted() == false ) {
	    	System.out.println("Customer #"+this.id);
	    	System.out.println("Name: "+this.name);
	    	System.out.println("Phone: "+this.phone);
	    	System.out.println("Email: "+this.email);
	    	System.out.println("-----------------");
	    	System.out.println(bookings.size()+" booking(s)");
	    	for(Booking booking: bookings) {
	    		booking.getDetailsShort();
	    	}
     	}
    }
    
    public List<Booking> getBookings() {
    	return new ArrayList<>(bookings);
    }
    public int getBookSize() {
        return bookings.size();
    }
    public void addBooking(Booking booking) throws FlightBookingSystemException {	
        // TODO: implementation here
    		for(Booking existing: bookings) {
    			if(existing.getFlightId() == booking.getFlightId()) {
    				throw new FlightBookingSystemException("Booking exist already!");
    			}
    		}
    		bookings.add(booking);  
    }
    public void cancelBooking(Booking booking) {	
        // TODO: implementation here
        bookings.remove(booking);
    }
}
