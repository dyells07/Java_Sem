import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VoterRegistrationGUI {
    private JFrame frame;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField districtField;
    private JTextField electorateField;
    private List<Voter> voters;
    private int selectedIndex = -1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VoterRegistrationGUI window = new VoterRegistrationGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VoterRegistrationGUI() {
        initialize();
        voters = new ArrayList<>();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel inputPanel = new JPanel();
        frame.getContentPane().add(inputPanel, BorderLayout.CENTER);
        inputPanel.setLayout(new GridLayout(4, 2, 0, 10));

        JLabel lblName = new JLabel("Name:");
        inputPanel.add(lblName);

        nameField = new JTextField();
        inputPanel.add(nameField);
        nameField.setColumns(10);

        JLabel lblAge = new JLabel("Age:");
        inputPanel.add(lblAge);

        ageField = new JTextField();
        inputPanel.add(ageField);
        ageField.setColumns(10);

        JLabel lblDistrict = new JLabel("District:");
        inputPanel.add(lblDistrict);

        districtField = new JTextField();
        inputPanel.add(districtField);
        districtField.setColumns(10);

        JLabel lblElectorate = new JLabel("Electorate:");
        inputPanel.add(lblElectorate);

        electorateField = new JTextField();
        inputPanel.add(electorateField);
        electorateField.setColumns(10);

        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        JButton btnRegister = new JButton("Register");
        buttonPanel.add(btnRegister);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerVoter();
            }
        });

        JButton btnEdit = new JButton("Edit");
        buttonPanel.add(btnEdit);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editVoter();
            }
        });
    }

    private void registerVoter() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String district = districtField.getText();
        String electorate = electorateField.getText();

        Voter voter = new Voter(name, age, district, electorate);
        voters.add(voter);

        JOptionPane.showMessageDialog(frame, "Voter registered successfully!");
        clearFields();
    }

    private void editVoter() {
        selectedIndex = getSelectedIndex();
        if (selectedIndex != -1) {
            Voter selectedVoter = voters.get(selectedIndex);
            nameField.setText(selectedVoter.getName());
            ageField.setText(String.valueOf(selectedVoter.getAge()));
            districtField.setText(selectedVoter.getDistrict());
            electorateField.setText(selectedVoter.getElectorate());
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        districtField.setText("");
        electorateField.setText("");
    }

    private int getSelectedIndex() {
        // For now, returning 0 for demonstration purposes
        return 0;
    }
}

class Voter {
    private String name;
    private int age;
    private String district;
    private String electorate;

    public Voter(String name, int age, String district, String electorate) {
        this.name = name;
        this.age = age;
        this.district = district;
        this.electorate = electorate;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDistrict() {
        return district;
    }

    public String getElectorate() {
        return electorate;
    }
}
