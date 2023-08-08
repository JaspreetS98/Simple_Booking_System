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


import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * ShowBook Window to show booking elements
 * 
 */
public class ShowBookWindows extends JFrame implements ActionListener {
	
	private MainWindow mw;
	private JTextField customerID = new JTextField();
	
	private JButton viewBtn = new JButton("View");
    private JButton cancelBtn = new JButton("Cancel");
	
    /**
     * ShowBookWindows constructor
     * 
     * @param mw, MainWindow 
     */
	public ShowBookWindows(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
	//Initialize the frame structure
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		
		setTitle("View Bookings");
		setSize(200, 200);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.add(new JLabel("Customer ID : "));
        panel.add(customerID);
        panel.add(viewBtn);
        panel.add(cancelBtn);
        
        viewBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        
        this.getContentPane().add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(mw);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == viewBtn) {
            viewBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

		
	}
	//find and view the bookings
	private void viewBook() {
		try {
            int customerId = Integer.parseInt(customerID.getText());
            Customer customer = FlightBookingSystem.getCustomerByID(customerId);   
            if(!customer.getisDeleted()) {
            	mw.displayBookings(customer);
                }else {
                	JOptionPane.showMessageDialog(this, "This Customer has been deleted from the system!");
                }
            
            this.setVisible(false);
		} catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
		
	}

	
}
