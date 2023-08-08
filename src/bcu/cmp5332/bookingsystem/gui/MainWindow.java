package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 * Main Window containing the rest
 * 
 */
public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu flightsMenu;
    private JMenu bookingsMenu;
    private JMenu customersMenu;

    private JMenuItem adminExit;

    private JMenuItem flightsView;
    private JMenuItem flightsAdd;
    private JMenuItem flightsDel;
    private JMenuItem flightsPass;
    private JMenuItem flightsNext;
    
    private JMenuItem bookingsIssue;
    private JMenuItem bookingsUpdate;
    private JMenuItem bookingsCancel;

    private JMenuItem custView;
    private JMenuItem custAdd;
    private JMenuItem custDel;
    private JMenuItem custBook;

    private FlightBookingSystem fbs;
    /**
     * MainWindow constructor
     * 
     * @param fbs, fbs
     */
    public MainWindow(FlightBookingSystem fbs) {

        initialize();
        this.fbs = fbs;
    }
    
    public FlightBookingSystem getFlightBookingSystem() {
        return fbs;
    }
    

    //Initialize the frame structure
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Flight Booking Management System");

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //adding adminMenu menu and menu items
        adminMenu = new JMenu("Admin");
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // adding Flights menu and menu items
        flightsMenu = new JMenu("Flights");
        menuBar.add(flightsMenu);

        flightsView = new JMenuItem("View");
        flightsAdd = new JMenuItem("Add");
        flightsDel = new JMenuItem("Delete");
        flightsPass = new JMenuItem("View Passengers");
        flightsNext = new JMenuItem("View future flights");
        flightsMenu.add(flightsView);
        flightsMenu.add(flightsAdd);
        flightsMenu.add(flightsDel);
        flightsMenu.add(flightsPass);
        flightsMenu.add(flightsNext);
        // creating action listener to Flights menu
        for (int i = 0; i < flightsMenu.getItemCount(); i++) {
            flightsMenu.getItem(i).addActionListener(this);
        }
        
        bookingsMenu = new JMenu("Bookings");
        menuBar.add(bookingsMenu);
        // adding Bookings menu and menu items
    
        bookingsIssue = new JMenuItem("Issue");
        bookingsUpdate = new JMenuItem("Update");
        bookingsCancel = new JMenuItem("Cancel");
        bookingsMenu.add(bookingsIssue);
        bookingsMenu.add(bookingsUpdate);
        bookingsMenu.add(bookingsCancel);
        // adding action listener for Bookings menu items
        for (int i = 0; i < bookingsMenu.getItemCount(); i++) {
            bookingsMenu.getItem(i).addActionListener(this);
        }

        // creating Customers menu and menu items
        customersMenu = new JMenu("Customers");
        menuBar.add(customersMenu);

        custView = new JMenuItem("View");
        custAdd = new JMenuItem("Add");
        custDel = new JMenuItem("Delete");
        custBook = new JMenuItem("View Bookings");
        

        customersMenu.add(custView);
        customersMenu.add(custAdd);
        customersMenu.add(custDel);
        customersMenu.add(custBook);
        // creating action listener for Customers menu items
        custView.addActionListener(this);
        custAdd.addActionListener(this);
        custDel.addActionListener(this);
        custBook.addActionListener(this);
        
        setSize(800, 500);
        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);   
        

    }	

    public static void main(String[] args) throws IOException, FlightBookingSystemException {
        FlightBookingSystem fbs = FlightBookingSystemData.load();
        new MainWindow(fbs);			
    }


    // creating cases for all posssible cases
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == adminExit) {
            try {
                FlightBookingSystemData.store(fbs);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        } else if (ae.getSource() == flightsView) {
            displayFlights();
            
        } else if (ae.getSource() == flightsAdd) {
            new AddFlightWindow(this);
            
        } else if (ae.getSource() == flightsDel) {
        	new DelFlightWindow(this);
        	
        } else if (ae.getSource() == flightsPass) {
        	new ShowPassWindow(this);
        
        } else if (ae.getSource() == flightsNext) {
        	displayFlightsNext();
        	
        } else if (ae.getSource() == bookingsIssue) {
        	new AddBookWindow(this);
            
        } else if (ae.getSource() == bookingsCancel) {
        	new CancelBookWindow(this);
            
        } else if (ae.getSource() == custView) {
        	displayCustomers();
            
        } else if (ae.getSource() == custAdd) {
        	new AddCustomerWindow(this);
            
        } else if (ae.getSource() == custDel) {
        	new DelCustomerWindow(this);
        	
	    } else if (ae.getSource() == custBook) {
	    	new ShowBookWindows(this);
	        
	    }
    }

    // displayFlights to display flights in table
    public void displayFlights() {
        List<Flight> flightsList = fbs.getFlights();
        // headers for the table
        String[] columns = new String[]{"Flight ID","Flight No", "Origin", "Destination", "Departure Date", "Flight Capacity", "Ticket Price"};
        Object[][] data = new Object[flightsList.size()][7];
        int j = 0;
        for (int i = 0; i < flightsList.size(); i++) {
            Flight flight = flightsList.get(i);
            if(flight.getisDeleted() == false) {	
            	data[j][0] = flight.getId();
	            data[j][1] = flight.getFlightNumber();
	            data[j][2] = flight.getOrigin();
	            data[j][3] = flight.getDestination();
	            data[j][4] = flight.getDepartureDate();
	            data[j][5] = flight.getFlightCapacity();
	            data[j][6] = flight.getTicketPrice();
	            j++;
            }
        }

        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    }
 // displayFlightsNext to display future flights in table
    public void displayFlightsNext() {
        List<Flight> flightsList = fbs.getFlights();
        // headers for the table
        String[] columns = new String[]{"Flight ID","Flight No", "Origin", 
        		"Destination", "Departure Date", "Flight Capacity", "Ticket Price"};
        Object[][] data = new Object[flightsList.size()][7];
        int j = 0;
        for (int i = 0; i < flightsList.size(); i++) {
            Flight flight = flightsList.get(i);
            //If flight not hidden
            if(!flight.getisDeleted()) {
            	//If flight departure date is after today
            	if(flight.getDepartureDate().isAfter(LocalDate.now())) {
            	data[j][0] = flight.getId();
	            data[j][1] = flight.getFlightNumber();
	            data[j][2] = flight.getOrigin();
	            data[j][3] = flight.getDestination();
	            data[j][4] = flight.getDepartureDate();
	            data[j][5] = flight.getFlightCapacity();
	            data[j][6] = flight.getTicketPrice();
	            j++;
            }
            }
        }

        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    }	
    // displayPassengers to display Passengers in table
    public void displayPassengers(Flight flight) {
        List<Customer> passengerList = flight.getPassengers();
        String[] columns = new String[]{"Id", "name", "Phone", "Email"};
        Object[][] data = new Object[passengerList.size()][4];
        int j = 0;
        for (int i = 0; i < passengerList.size(); i++) {
            Customer passenger = passengerList.get(i);
            if(!passenger.getisDeleted()) {	
	            data[j][0] = passenger.getId();
	            data[j][1] = passenger.getName();
	            data[j][2] = passenger.getPhone();
	            data[j][3] = passenger.getEmail();
	            j++;
            }
        }

        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    }
    
    public void displayCustomers() {
    	//Get customers from the system
    	List<Customer> customersList = fbs.getCustomers();
    	String[] columns = new String[]{"Id","Name", "Phone", "Email", "No of Bookings"}; 	
        Object[][] data = new Object[customersList.size()][5];
        int j = 0;
        for (int i = 0; i < customersList.size(); i++) {
        	Customer customer = customersList.get(i);
        	//If customer not hidden/deleted
        	if(!customer.getisDeleted()) {	
	        	data[j][0] = customer.getId();
	            data[j][1] = customer.getName();
	            data[j][2] = customer.getPhone();
	            data[j][3] = customer.getEmail();
	            data[j][4] = customer.getBookSize();
	            j++;
	            
            }
        }

        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    } 
    // displayBookings to display bookings in table
    public void displayBookings(Customer customer) throws FlightBookingSystemException {
        List<Booking> bookingList = customer.getBookings();
        // headers for the table
        String[] columns = new String[]{"Flight ID", "Flight Number", 
        		"Departure Date", "Origin", "Destination", 
        		"Booking Date", "Booking Price"};

        Object[][] data = new Object[bookingList.size()][7];
        for (int i = 0; i < bookingList.size(); i++) {
        	Booking booking = bookingList.get(i);
        	Flight flight = FlightBookingSystem.getFlightByID(booking.getFlightId());
            data[i][0] = booking.getFlightId();
            data[i][1] = flight.getFlightNumber();
            data[i][2] = flight.getDepartureDate();
            data[i][3] = flight.getOrigin();
            data[i][4] = flight.getDestination();
            data[i][5] = booking.getBookingDate();
            data[i][6] = booking.getBookingPrice();

        }

        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    }	

    
    
    
    
    
}
