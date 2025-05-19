import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class mainform extends JFrame {
    private JTextField nameField, ageField, addressField, phoneNumberField;

    public mainform() {
        setTitle("Student Registration Form");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("Address:"));
        addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        panel.add(phoneNumberField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });
        panel.add(saveButton);

        add(panel);
        setVisible(true);
    }

    private void saveToFile() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();

        try (PrintWriter writer = new PrintWriter(new FileWriter("students.txt", true))) {
            writer.println("Name: " + name);
            writer.println("Age: " + age);
            writer.println("Address: " + address);
            writer.println("Phone Number: " + phoneNumber);
            writer.println();
            writer.flush();
            JOptionPane.showMessageDialog(this, "Student details saved to file.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new mainform();
            }
        });
    }
}
