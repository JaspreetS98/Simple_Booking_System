package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Delete flight
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class DeleteFlight implements Command {
	private int flightID;
	
	 /**
     * 
     * DeleteFlight Constructor
     * 
     * @param flightID (integer) flight's id to delete
     * 
     */
    public DeleteFlight(int flightID) {
    	this.flightID = flightID;
    }

    /**
     * 
     * Execute DeleteFlight command
     * 
     * @throws FlightBookingSystemException if ID not found.
     * 
     */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		 Flight flight = flightBookingSystem.getFlightByID(flightID);
		 flight.setisDeleted(true);
         System.out.println(flight.getFlightNumber()+" is not longer a part of the system.");
         try {
         	//Save the status of the system
 			FlightBookingSystemData.store(flightBookingSystem);
 		} catch (IOException e) {
 			e.printStackTrace();
 			}
        }     
}

