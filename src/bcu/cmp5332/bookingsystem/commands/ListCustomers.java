package bcu.cmp5332.bookingsystem.commands;

import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
/**
 * List all customers
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class ListCustomers implements Command {
	/**
     * Execute ListCustomers command
     * 
     * @throws FlightBookingSystemException if data is missing
     */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		//Get customer list 
        	List<Customer> customers = flightBookingSystem.getCustomers();
        	//
            for (Customer customer : customers) {
            	if(customer.getisDeleted() == false) {	
            		System.out.println(customer.getDetailsShort());
            	}
            }
            System.out.print(customers.size() + " customers(s)");
        }
	}


