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
 * DelCustomer Window to delete customer elements
 * 
 */
public class DelCustomerWindow extends JFrame implements ActionListener {
	private MainWindow mw;
	private JTextField customerID = new JTextField();
	
	private JButton delBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");
    /**
     * DelCustomerWindow constructor
     * 
     * @param mw, MainWindow 
     */
	public DelCustomerWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
	//Initialize the frame structure
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		
		setTitle("Delete Customers");
		setSize(200, 200);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.add(new JLabel("Customer ID : "));
        panel.add(customerID);
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
            delCust();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

		
	}
	//find and delete the customer
	private void delCust() {
		try {
            int customerId = Integer.parseInt(customerID.getText());
            Customer customer = FlightBookingSystem.getCustomerByID(customerId);
            customer.setisDeleted(true);
            JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
            mw.displayCustomers();
            
            this.setVisible(false);
		} catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
		
	}

	
}
