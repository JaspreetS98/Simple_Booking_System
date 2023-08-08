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

/**
 * ShowPass Window to show passenger elements of a flght
 * 
 */
public class ShowPassWindow extends JFrame implements ActionListener {
	
	private MainWindow mw;
	private JTextField flightID = new JTextField();
	
	private JButton viewBtn = new JButton("View");
    private JButton cancelBtn = new JButton("Cancel");
    /**
     * ShowPassWindow constructor
     * 
     * @param mw, MainWindow 
     */
	public ShowPassWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
	//Initialize the frame structure
	private void initialize() {
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		
		setTitle("View passengers");
		setSize(200, 200);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.add(new JLabel("Flight ID : "));
        panel.add(flightID);
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
            try {
				viewPass();
			} catch (FlightBookingSystemException e) {
				e.printStackTrace();
			}
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

		
	}
	//find and view the passengers
	private void viewPass() throws FlightBookingSystemException {
		try {
            int flightId = Integer.parseInt(flightID.getText());
            Flight flight = FlightBookingSystem.getFlightByID(flightId);
            if(!flight.getisDeleted()) {
            mw.displayPassengers(flight);
            }else {
            	JOptionPane.showMessageDialog(this, "This Flight has been deleted from the system!");
            }
            this.setVisible(false);
		} catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
		
	}

	

}
