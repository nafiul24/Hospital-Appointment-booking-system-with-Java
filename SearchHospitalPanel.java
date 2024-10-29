package ui;

import model.Hospital;
import util.CSVReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SearchHospitalPanel extends JFrame {
    private CSVReader csvReader;
    private JTable hospitalTable;

    public SearchHospitalPanel() {
        this.csvReader = new CSVReader();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Search Hospital");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        List<Hospital> hospitals = csvReader.readHospitalsFromCSV("D:\\\\1603 INFORMATION SYSTEM AND ORGANISATION\\\\semester 2\\\\hospital-info.csv");
        String[] columnNames = {"ID", "Hospital Name", "Address", "City", "Contact"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Hospital hospital : hospitals) {
            Object[] row = {hospital.getId(), hospital.getName(), hospital.getAddress(), hospital.getContact()};
            model.addRow(row);
        }

        hospitalTable = new JTable(model);
        hospitalTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = hospitalTable.getSelectedRow();
                if (row != -1) {
                    String id = hospitalTable.getValueAt(row, 0).toString();
                    String name = hospitalTable.getValueAt(row, 1).toString();
                    String address = hospitalTable.getValueAt(row, 2).toString();
                   
                    String contact = hospitalTable.getValueAt(row, 3).toString();

                    String message = String.format("Hospital Details:\nID: %s\nName: %s\nAddress %s\nContact: %s",
                            id, name, address, contact);
                    JOptionPane.showMessageDialog(SearchHospitalPanel.this, message, "Hospital Details", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(hospitalTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchHospitalPanel().setVisible(true));
    }
}
