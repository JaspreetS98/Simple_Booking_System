package bcu.cmp5332.bookingsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
//Test flight and customer methods to obtain precise info.
class TestCase {
	private final Flight flight = new Flight(1 , "MM0001" , "Madrid" , "Manchester" , LocalDate.parse("2021-02-26"), 70 , 110.0 ,false);
	private final Customer customer  = new Customer(1 , "Joe" , "019995678" , "Joe@gmail.com" , false);
	Double price  =  110.0;
	@Test
	public void testFlight() throws Exception {
		assertEquals(70, flight.getFlightCapacity());
		assertEquals(price, flight.getTicketPrice());
	}
	@Test
	public void testCustomer() throws Exception {
		assertEquals("Joe@gmail.com" , customer.getEmail());
	}
}
