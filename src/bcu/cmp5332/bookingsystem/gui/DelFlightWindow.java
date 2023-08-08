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
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * DelFlight Window to delete flight elements
 * 
 */
public class DelFlightWindow extends JFrame implements ActionListener {
	private MainWindow mw;
	private JTextField flightID = new JTextField();
	
	private JButton delBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");
    /**
     * DelFlightWindow constructor
     * 
     * @param mw, MainWindow 
     */
	public DelFlightWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
	//Initialize the frame structure
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
		setTitle("Delete Flight");
		setSize(200, 200);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
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
            delFlight();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

		
	}
	//find and delete the flight
	private void delFlight() {
		try {
            int flightId = Integer.parseInt(flightID.getText());
            Flight flight = FlightBookingSystem.getFlightByID(flightId);
            flight.setisDeleted(true);
            JOptionPane.showMessageDialog(this, "Flight deleted successfully!");
            mw.displayFlights();
            
            this.setVisible(false);
		} catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
		
	}
}
