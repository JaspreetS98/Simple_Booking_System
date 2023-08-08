package bcu.cmp5332.bookingsystem.model;

import java.time.*;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.util.*;

public class FlightBookingSystem {
    
    private final LocalDate systemDate = LocalDate.now();
    
    private final static Map<Integer, Customer> customers = new TreeMap<>();
    private final static Map<Integer, Flight> flights = new TreeMap<>();
    
    //Get lists of the data and exceptions
    public LocalDate getSystemDate() {
        return systemDate;
    }

    public List<Flight> getFlights() {
        List<Flight> out = new ArrayList<>(flights.values());
        return Collections.unmodifiableList(out);
    }
    
    public List<Customer> getCustomers() {
        List<Customer> out = new ArrayList<>(customers.values());
        return Collections.unmodifiableList(out);
    }
    
    public static  Flight getFlightByID(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("There is no flight with that ID.");
        }
        return flights.get(id);
    }

    public static Customer getCustomerByID(int id) throws FlightBookingSystemException {
    	if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("There is no customer with that ID.");
        }
        return customers.get(id);
    }
    

    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Duplicate flight ID.");
        }
        for (Flight existing : flights.values()) {
            if (existing.getFlightNumber().equals(flight.getFlightNumber()) 
                && existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
                throw new FlightBookingSystemException("There is a flight with same "
                        + "number and departure date in the system");
            }
        }
        flights.put(flight.getId(), flight);
    }

    public void addCustomer(Customer customer) throws FlightBookingSystemException {
    	//Duplicate ID check
        if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Duplicate customer ID.");
        }
        // TODO: implementation here
        customers.put(customer.getId(), customer);
    }
    
    
    
    
    
    
    
}