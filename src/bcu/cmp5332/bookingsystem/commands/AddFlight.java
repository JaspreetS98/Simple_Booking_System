package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;
import java.time.LocalDate;
/**
 * Add new flight to the system
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class AddFlight implements  Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private int capacity;
    private double ticketPrice;
    private boolean isDeleted = false;
    
    /**
     * AddFlight constructor
     * 
     * @param flightNumber of the flight (string)
     * @param origin of the flight (string)
     * @param destination of the flight (string)
     * @param departureDate of the flight (LocalDate)
     * @param capacity of the flight (int)
     * @param ticketPrice of the flight (double)
     */
    public AddFlight(String flightNumber, String origin, String destination, LocalDate departureDate, int capacity, double ticketPrice) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
    }
    /**
     * Execute AddFlight command
     * 
     * @throws FlightBookingSystemException if flightNumber, origin, destination, departureDate, capacity or ticketPrice are missing
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	//Give the flight the next ID
        int maxId = 0;
        if (flightBookingSystem.getFlights().size() > 0) {
            int lastIndex = flightBookingSystem.getFlights().size() - 1;
            maxId = flightBookingSystem.getFlights().get(lastIndex).getId();
        }
        //Create flight
        Flight flight = new Flight(++maxId, flightNumber, origin, destination, departureDate, capacity, ticketPrice, isDeleted);
        //Add flight to system
        flightBookingSystem.addFlight(flight);
        System.out.println("Flight #" + flight.getId() + " added.");
        
        try {
        	//Save the status of the system
			FlightBookingSystemData.store(flightBookingSystem);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
