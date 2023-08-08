package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;
/**
 * List all the flights 
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class ListFlights implements Command {
	/**
     * Execute ListFlights command
     * 
     * @throws FlightBookingSystemException if data is missing
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Flight> flights = flightBookingSystem.getFlights();
        for (Flight flight : flights) {
        	if(flight.getisDeleted() == false) {	
        		System.out.println(flight.getDetailsShort());
        	}   
        }
        System.out.println(flights.size() + " flight(s)");
    }
}
