package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;


/**
 * AddBook Window to add new booking element
 * 
 */
public class AddBookWindow extends JFrame implements ActionListener{
    private MainWindow mw;
    private JTextField customerID = new JTextField();
    private JTextField flightID = new JTextField(); 
    private JButton addBtn = new JButton("Issue");
    private JButton cancelBtn = new JButton("Cancel");
    /**
     * AddBookWindow constructor
     * 
     * @param mw, MainWindow 
     */
    public AddBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    //Initialize the frame structure
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Issue a New Booking");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));
        topPanel.add(new JLabel("Customer ID : "));
        topPanel.add(customerID);
        topPanel.add(new JLabel("Flight ID : "));
        topPanel.add(flightID);
        
       
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }
	
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            addBooking();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    //add a new booking element
    private void addBooking() {
        try {
            int customerId = Integer.parseInt(customerID.getText());
            int flightId = Integer.parseInt(flightID.getText());          
            
            // create and execute the addBooking Command
            Command addBooking = new AddBooking(customerId,flightId);
            
            addBooking.execute(mw.getFlightBookingSystem());
            JOptionPane.showMessageDialog(this, "Booking issued successfully!");
            
            // refresh the list of Customers
            mw.displayCustomers();
            // close the AddbookingWindow
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

	
	
}
