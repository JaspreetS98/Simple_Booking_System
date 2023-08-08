package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
/**
 * List flights details 
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class ShowFlight implements Command{
	int id;
	
	public ShowFlight(int id) {
		this.id = id;
	}
	/**
     * Execute ShowFlight command
     * 
     * @throws FlightBookingSystemException if data is missing
     */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		 Flight flight = flightBookingSystem.getFlightByID(id);
		 if(flight != null) {
			 System.out.println(flight.getDetailsShort());
		 }		
	}
}
