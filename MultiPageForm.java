import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiPageForm extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MultiPageForm() {
        setTitle("Multi-Page Form");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create cards (pages)
        JPanel loginCard = createLoginCard();
        JPanel welcomeCard = createWelcomeCard();
        JPanel settingsCard = createSettingsCard();

        // Create card panel with CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(loginCard, "login");
        cardPanel.add(welcomeCard, "welcome");
        cardPanel.add(settingsCard, "settings");

        add(cardPanel);

        // Display the frame
        setVisible(true);

        // Show the login page initially
        cardLayout.show(cardPanel, "login");
    }

    private JPanel createLoginCard() {
        JPanel loginCard = new JPanel(new GridLayout(3, 2, 5, 5));

        // Create components for the login card
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace this with your actual login validation logic
                String enteredUsername = usernameField.getText();
                char[] enteredPassword = passwordField.getPassword();

                // Example: Check if entered username is "admin" and password is "password"
                if ("admin".equals(enteredUsername) && "password".equals(new String(enteredPassword))) {
                    cardLayout.show(cardPanel, "welcome");
                } else {
                    JOptionPane.showMessageDialog(MultiPageForm.this, "Invalid username or password");
                }
            }
        });

        loginCard.add(usernameLabel);
        loginCard.add(usernameField);
        loginCard.add(passwordLabel);
        loginCard.add(passwordField);
        loginCard.add(new JLabel()); // Empty label for spacing
        loginCard.add(loginButton);

        return loginCard;
    }

    private JPanel createWelcomeCard() {
        JPanel welcomeCard = new JPanel(new BorderLayout());

        // Create components for the welcome card
        JLabel welcomeLabel = new JLabel("Welcome to the Application!");
        JButton goToSettingsButton = new JButton("Go to Settings");

        goToSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "settings");
            }
        });

        JButton goBackToLoginButton = new JButton("Go Back to Login");
        goBackToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "login");
            }
        });

        welcomeCard.add(welcomeLabel, BorderLayout.NORTH);
        welcomeCard.add(goToSettingsButton, BorderLayout.CENTER);
        welcomeCard.add(goBackToLoginButton, BorderLayout.SOUTH);

        return welcomeCard;
    }

    private JPanel createSettingsCard() {
        JPanel settingsCard = new JPanel(new BorderLayout());

        // Create components for the settings card
        JLabel settingsLabel = new JLabel("Settings Page");
        JButton goBackToWelcomeButton = new JButton("Go Back to Welcome");

        goBackToWelcomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "welcome");
            }
        });

        settingsCard.add(settingsLabel, BorderLayout.NORTH);
        settingsCard.add(goBackToWelcomeButton, BorderLayout.SOUTH);

        return settingsCard;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MultiPageForm();
            }
        });
    }
}
