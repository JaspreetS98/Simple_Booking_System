package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class BookingDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/bookings.txt";
    // Loading booking's data into the txt file
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
    	 try (Scanner sc = new Scanner(new File(RESOURCE))) {
             int line_idx = 1;
             while (sc.hasNextLine()) {
                 String line = sc.nextLine();
                 String[] properties = line.split(SEPARATOR, -1);
                 try {
                     int customerID = Integer.parseInt(properties[0]);
                     int flightID = Integer.parseInt(properties[1]);;
                     LocalDate bookingDate = LocalDate.parse(properties[2]);
                     double bookingPrice = Double.parseDouble((properties[3]));
                     Booking booking = new Booking(customerID,flightID,bookingDate);
                     booking.setBookingPrice(bookingPrice);
                     Customer customer = fbs.getCustomerByID(customerID);
                     Flight flight = FlightBookingSystem.getFlightByID(flightID);
                     customer.addBooking(booking);
                     flight.addPassenger(customer);
                 } catch (NumberFormatException ex) {
                     throw new FlightBookingSystemException("Unable to parse book id " 
                    		 + properties[0] + " on line " + line_idx
                         + "\nError: " + ex);
                 }
                 line_idx++;
             }
         }
    }
    // Saving booking's data into the txt file
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
    		for (Customer customer : fbs.getCustomers()) {
            	for(Booking booking: customer.getBookings()) {
            		out.print(booking.getCustomerId() + SEPARATOR);
            		out.print(booking.getFlightId() + SEPARATOR);
            		out.print(booking.getBookingDate() + SEPARATOR);
            		out.print(booking.getBookingPrice() + SEPARATOR);
            		out.println();
            	}
    		}
        }
    }
       
}
