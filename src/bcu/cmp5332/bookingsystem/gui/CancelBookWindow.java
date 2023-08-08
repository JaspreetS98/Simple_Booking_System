package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.model.*;
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

import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * CancelBook Window to delete booking elements
 * 
 */
public class CancelBookWindow extends JFrame implements ActionListener {
	private MainWindow mw;
	private JTextField customerID = new JTextField();
	private JTextField flightID = new JTextField();
	
	private JButton delBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");
    /**
     * CancelBookWindow constructor
     * 
     * @param mw, MainWindow 
     */
	public CancelBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
	//Initialize the frame structure
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		setTitle("Cancel Booking");
		setSize(200, 200);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		panel.add(new JLabel("Customer ID : "));
        panel.add(customerID);
        panel.add(new JLabel("Flight ID : "));
        panel.add(flightID);
        panel.add(delBtn);
        panel.add(cancelBtn);
        
        delBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        
        this.getContentPane().add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(mw);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == delBtn) {
            cancBooking();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }	
	}
	//find and delete the booking
	private void cancBooking() {
		try {
            int customerId = Integer.parseInt(customerID.getText());
            int flightId = Integer.parseInt(flightID.getText());
            Command cancelBooking = new CancelBooking(customerId,flightId);
            cancelBooking.execute(mw.getFlightBookingSystem());
            JOptionPane.showMessageDialog(this, "Booking Canceled successfully!");
            this.setVisible(false);
		} catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
		
	}
}
