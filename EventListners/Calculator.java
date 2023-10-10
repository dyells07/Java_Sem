import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    private JFrame frame;
    private JTextField display;
    private String currentInput = "";
    private double result = 0;
    private String lastOperator = "";
    private boolean isNewInput = true;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            if (isNewInput) {
                display.setText("");
                currentInput = "";
                isNewInput = false;
            }

            if (buttonText.matches("[0-9]")) {
                currentInput += buttonText;
                display.setText(display.getText() + buttonText);
            } else if (buttonText.matches("[/+*-]")) {
                if (!currentInput.isEmpty()) {
                    double inputNumber = Double.parseDouble(currentInput);
                    if (lastOperator.equals("+")) {
                        result += inputNumber;
                    } else if (lastOperator.equals("-")) {
                        result -= inputNumber;
                    } else if (lastOperator.equals("*")) {
                        result *= inputNumber;
                    } else if (lastOperator.equals("/")) {
                        if (inputNumber != 0) {
                            result /= inputNumber;
                        } else {
                            display.setText("Error");
                            return;
                        }
                    } else {
                        result = inputNumber;
                    }
                }
                currentInput = "";
                lastOperator = buttonText;
            } else if (buttonText.equals("=")) {
                if (!currentInput.isEmpty()) {
                    double inputNumber = Double.parseDouble(currentInput);
                    if (lastOperator.equals("+")) {
                        result += inputNumber;
                    } else if (lastOperator.equals("-")) {
                        result -= inputNumber;
                    } else if (lastOperator.equals("*")) {
                        result *= inputNumber;
                    } else if (lastOperator.equals("/")) {
                        if (inputNumber != 0) {
                            result /= inputNumber;
                        } else {
                            display.setText("Error");
                            return;
                        }
                    }
                    display.setText(Double.toString(result));
                    isNewInput = true;
                }
            } else if (buttonText.equals("C")) {
                currentInput = "";
                result = 0;
                lastOperator = "";
                display.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });
    }
}