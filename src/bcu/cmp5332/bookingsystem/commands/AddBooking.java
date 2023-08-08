package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.data.*;

/**
 * Add new booking
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class AddBooking implements Command {
	private int customerID;
    private int flightID;
    /**
     * AddBooking constructor
     * 
     * @param customerID  integer id of the customer who is booking the flight
     * @param flightID  integer id of the flight
     */
    public AddBooking(int customerID, int flightID) {
    	this.customerID = customerID;
    	this.flightID = flightID;
    }
    /**
     * Execute AddBooking command
     * 
     * @throws FlightBookingSystemException if customer ID , flight ID are not found or if flight is full
     */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		
		//Get flight using ID
        Flight flight = FlightBookingSystem.getFlightByID(flightID);
        //Check if flight full
        if(flight.getNumberPassengers() < flight.getFlightCapacity()) {
        	//Get customer using ID
        	Customer customer = FlightBookingSystem.getCustomerByID(customerID);
        	//Get local date
    		LocalDate bookingDate = flightBookingSystem.getSystemDate();
    		//create new booking
    		Booking booking = new Booking(customer.getId(), flight.getId(), bookingDate);
    		//Add the booking to the customer's info
            customer.addBooking(booking);
            //Add customer to the flight's passengers
            flight.addPassenger(customer);
            System.out.println(customer.getName()+" is now passenger of flight "+flight.getFlightNumber());
            System.out.println(" Number of passengers: "+flight.getNumberPassengers());
            System.out.println(" Max capacity: "+flight.getFlightCapacity());
    	}else {	
            throw new FlightBookingSystemException("Sorry, flight "+flight.getFlightNumber()+" is full!");
    	}
        try {
        	//Save the status of the system
			FlightBookingSystemData.store(flightBookingSystem);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 	
}
