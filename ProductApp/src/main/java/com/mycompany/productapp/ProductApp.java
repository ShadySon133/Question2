

package com.mycompany.productapp;

/**
 *
 * @author Shady
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {

    // GUI Components
    private JTextField txtCustomerName;
    private JComboBox<String> cmbProduct;
    private JTextField txtPrice;
    private JButton btnProcess;

    public ProductApp() {
        setTitle("Product App");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); 

        //Menu System 
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        //Form Components
        JLabel lblName = new JLabel("Customer Name:");
        lblName.setBounds(30, 30, 120, 25);
        add(lblName);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(150, 30, 200, 25);
        add(txtCustomerName);

        JLabel lblProduct = new JLabel("Select Product:");
        lblProduct.setBounds(30, 70, 120, 25);
        add(lblProduct);

        // Combo Box Options
        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        cmbProduct = new JComboBox<>(products);
        cmbProduct.setBounds(150, 70, 200, 25);
        add(cmbProduct);

        JLabel lblPrice = new JLabel("Enter Price:");
        lblPrice.setBounds(30, 110, 120, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(150, 110, 200, 25);
        add(txtPrice);

        btnProcess = new JButton("PROCESS");
        btnProcess.setBounds(150, 160, 200, 30);
        add(btnProcess);

        // Action Listeners
        
        // Process Button Logic (Q.2.3)
        btnProcess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSale();
            }
        });

      
        processItem.addActionListener(e -> processSale());

        // Menu "Clear" item 
        clearItem.addActionListener(e -> {
            txtCustomerName.setText("");
            txtPrice.setText("");
            cmbProduct.setSelectedIndex(0);
        });
    }

    // to calculate, display popup, and write to file
    private void processSale() {
        try {
            String customerName = txtCustomerName.getText();
            String productName = (String) cmbProduct.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText());

            // Pass price to the calculation class
            ProductCalculations calc = new ProductCalculations(price);
            double vat = calc.GetVat();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

            // Format message string
            String message = "Customer Name: " + customerName + "\n" +
                             "Product: " + productName + "\n" +
                             "VAT: R " + vat + "\n" +
                             "COMMISSION: R " + commission + "\n" +
                             "TOTAL: R " + total;

            // Show Popup (Screenshot 2)
            JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);

            // Save to File (Q.2.3)
            saveToFile(customerName, productName, vat, commission, total);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // File writing method
    private void saveToFile(String name, String product, double vat, double comm, double total) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt", true))) {
            writer.println("DATA LOG");
            writer.println("*************************");
            writer.println("Customer Name: " + name);
            writer.println("Product: " + product);
            writer.println("VAT: R " + vat);
            writer.println("COMMISSION: R " + comm);
            writer.println("TOTAL: R " + total);
            writer.println("*************************");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method to run the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductApp().setVisible(true);
        });
    }
}
