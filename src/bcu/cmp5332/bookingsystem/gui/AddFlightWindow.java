package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * AddFlight Window to add new flight elements
 * 
 */
public class AddFlightWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField flightNoText = new JTextField();
    private JTextField originText = new JTextField();
    private JTextField destinationText = new JTextField();
    private JTextField depDateText = new JTextField();
    private JTextField capacityText = new JTextField();
    private JTextField ticketPriceText = new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");
    /**
     * AddFlightWindow constructor
     * 
     * @param mw, MainWindow 
     */
    public AddFlightWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    //Initialize the frame structure
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Add a New flight");

        setSize(350, 350);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(7, 2));
        topPanel.add(new JLabel("Flight No : "));
        topPanel.add(flightNoText);
        topPanel.add(new JLabel("Origin : "));
        topPanel.add(originText);
        topPanel.add(new JLabel("Destination : "));
        topPanel.add(destinationText);
        topPanel.add(new JLabel("Departure Date (YYYY-MM-DD) : "));
        topPanel.add(depDateText);
        topPanel.add(new JLabel("Flight Capacity : "));
		topPanel.add(capacityText);
        topPanel.add(new JLabel("Ticket Price : "));
        topPanel.add(ticketPriceText);

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
            addFlight();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    //add a new flight element
    private void addFlight() {
        try {
            String flightNumber = flightNoText.getText();
            String origin = originText.getText();
            String destination = destinationText.getText();
            LocalDate departureDate = null;
            
            int capacity = Integer.parseInt(capacityText.getText());
            double ticketPrice = Double.parseDouble(ticketPriceText.getText());
            try {
                departureDate = LocalDate.parse(depDateText.getText());
            }
            catch (DateTimeParseException dtpe) {
                throw new FlightBookingSystemException("Date must be in YYYY-DD-MM format");
            }
            // create and execute the AddFlight Command
            Command addFlight = new AddFlight(flightNumber, origin, destination, departureDate, capacity, ticketPrice);
            addFlight.execute(mw.getFlightBookingSystem());
            JOptionPane.showMessageDialog(this, "Flight added successfully!");
            // refresh the list of flights
            mw.displayFlights();
            // close the AddFlightWindow
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
