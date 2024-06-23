package cl.jay.clock.util;

import javax.swing.*;

public class Messages {

    private Messages() {
        throw new IllegalStateException("Utility class");
    }

    public static void errorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
