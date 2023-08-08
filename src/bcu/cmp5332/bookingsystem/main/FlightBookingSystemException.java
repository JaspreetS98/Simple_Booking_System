package bcu.cmp5332.bookingsystem.main;

/**
 * FlightBookingSystemException extends {@link Exception} class, notify the user about errors
 * 
 */
public class FlightBookingSystemException extends Exception {

	private static final long serialVersionUID = 1L;

	public FlightBookingSystemException(String message) {
        super(message);
    }
}
