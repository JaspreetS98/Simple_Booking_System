package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.util.List;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
/**
 * Cancel a booking
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class CancelBooking implements Command {
	private int customerID;
    private int flightID;
    boolean deleted = false;
    /**
     * AddBooking constructor
     * 
     * @param customerID  (integer) id of the customer who is booking the flight
     * @param flightID  (integer) id of the flight
     * @throws bcu.cmp5332.bookingsystem.main.FlightBookingSystemException if data it's not found
     */
    public CancelBooking(int customerID, int flightID) throws FlightBookingSystemException {
    	this.customerID = customerID;
    	this.flightID = flightID;
    }
    /**
     * Execute CancelBooking command
     * 
     * @throws FlightBookingSystemException 
     * (if customer ID or flight ID are missing)
     */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		//Get customer using ID
		Customer customer = flightBookingSystem.getCustomerByID(customerID);
		//Get flight by ID
        Flight flight = FlightBookingSystem.getFlightByID(flightID);
        //Get customer's bookings 
        List<Booking> bookingList = customer.getBookings();
        //Select Booking to delete
        for(Booking booking: bookingList) {
        	//If found
        	if(booking.getFlightId()==flightID) {
        		//Cancel booking
        		customer.cancelBooking(booking);
        		//Cancel customer from passengers
        		flight.cancelPassenger(customer);
        		System.out.println(customer.getName()+
        				" is not longer passenger of flight "+flight.getFlightNumber());
        		deleted = true;
        		break;
        	}
        }
        //If not found
        if (deleted == false) {
        	throw new FlightBookingSystemException("This booking does not exist!!!");
        }   
        try {
        	//Save the status of the system
			FlightBookingSystemData.store(flightBookingSystem);
		} catch (IOException e) {
			e.printStackTrace();
			}
        }
}
