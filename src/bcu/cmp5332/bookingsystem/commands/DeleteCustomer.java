package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Delete customer
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class DeleteCustomer implements Command {
	int customerID;     
	/**
     * DeleteCustomer constructor
     * 
     * @param customerID (integer) customer's id to delete
     * 
     */
    public DeleteCustomer(int customerID) {
    	this.customerID = customerID;
    } 
    /**
     * 
     * Execute delete customer command
     * 
     * @throws FlightBookingSystemException if ID not found
     * 
     */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		 Customer customer = flightBookingSystem.getCustomerByID(customerID);
         customer.setisDeleted(true);
         System.out.println(customer.getName()+" is not longer a customer of the system.");
         try {
 			FlightBookingSystemData.store(flightBookingSystem);
 		} catch (IOException e) {
 			e.printStackTrace();
 			}
       	}     
}
