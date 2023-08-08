package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
/**
 * Add new customer
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class AddCustomer implements Command {
    private final String name;
    private final String phone;
    private final String email;
    private boolean isDeleted = false;
    /**
     * AddCustomer constructor
     * 
     * @param name of the customer (string)
     * @param phone of the customer (string)
     * @param email of the customer (string)
     */
    public AddCustomer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    /**
     * Execute AddCustomer command
     * 
     * @throws FlightBookingSystemException if name, phone or email are missing
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        //Give new customer the next ID
    	int maxId = 0;
        if (flightBookingSystem.getCustomers().size() > 0) {
            int lastIndex = flightBookingSystem.getCustomers().size() - 1;
            maxId = flightBookingSystem.getCustomers().get(lastIndex).getId();
        }
        //Create customer
        Customer customer = new Customer(++maxId, name, phone,email,isDeleted);
        //Add customer to system
        flightBookingSystem.addCustomer(customer);
        System.out.println("Customer #" + customer.getId() + " added.");
        
        try {
        	//Save the status of the system
			FlightBookingSystemData.store(flightBookingSystem);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
  }

