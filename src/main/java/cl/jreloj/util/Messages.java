package cl.jreloj.util;

import javax.swing.*;

public class Messages {
    public static void errorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
