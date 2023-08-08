package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
/**
 * List customer's details
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class ShowCustomer implements Command {
	int id;
	
	public ShowCustomer(int id) {
		this.id = id;
	}
	/**
     * Execute ShowCustomer command
     * 
     * @throws FlightBookingSystemException if data is missing
     */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		 Customer customer = flightBookingSystem.getCustomerByID(id);
		 if(customer != null ) {
			 customer.getDetailsLong();
		 }		
	}
}
