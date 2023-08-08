package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.gui.MainWindow;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
/**
 * 
 * Load GUI version of the program
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class LoadGUI implements Command {
	/**
     * Execute LoadGUI command
     * 
     * @throws FlightBookingSystemException if something goes wrong
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        new MainWindow(flightBookingSystem);
    }
    
}
