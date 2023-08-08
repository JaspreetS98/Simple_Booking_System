package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flight {
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int capacity;
    private double ticketPrice;
    int  numberPassengers = 0;
    private Boolean isDeleted;
    private final Set<Customer> passengers;
	// Flight constructor
    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, int capacity, double ticketPrice, Boolean isDeleted) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice; 
        passengers = new HashSet<>();
        this.isDeleted = isDeleted;      
    }
    // implementation of Getter and Setter methods
    public int getId() {
        return id;
    }
    public int getNumberPassengers() {
    	return numberPassengers;
    }
    public int getFlightCapacity(){
    	return capacity;
    }
    public double getTicketPrice(){
    	return ticketPrice;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFlightCapacity(int capacity){
    	this.capacity = capacity;
    }
    public void setTicketPrice(double ticketPrice){
    	this.ticketPrice = ticketPrice;
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf);
    }
    public void getDetailsLong() throws FlightBookingSystemException{
        // TODO: implementation here
    	if (getisDeleted() == false ) {
	    	System.out.println("Flight #"+this.id);
	    	System.out.println("Flight Number: "+this.flightNumber);
	    	System.out.println("Origin: "+this.origin);
	    	System.out.println("Destination: "+this.destination);
	    	System.out.println("Departure Date: "+this.departureDate);
	    	System.out.println("Ticket Price: "+this.ticketPrice);
	    	System.out.println("Capacity: "+this.capacity);
	    	System.out.println("-----------------");
	    	System.out.println(passengers.size()+" passenger(s)");	
	    	for(Customer customer: passengers) {
	    		customer.getDetailsShort();
	    	}
     	}  
    } 
    public void addPassenger(Customer passenger) {
        passengers.add(passenger);
        numberPassengers++;
    }
    public void cancelPassenger(Customer passenger) {
        passengers.remove(passenger);
        numberPassengers--;
    }
    public Boolean getisDeleted() {
        return isDeleted;
    }
    public void setisDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
 
    
    
}
