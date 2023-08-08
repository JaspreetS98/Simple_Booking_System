package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
/**
 * print help message command
 * 
 * @author Jaspreet Singh and Roheel Ahmed
 * 
 */
public class Help implements Command {

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) {
        System.out.println(Command.HELP_MESSAGE);
    }
}
