package cl.jay.clock.util;

import javax.swing.*;

public class Resize {
    public static void resize(JFrame jFrame, boolean resizeable, String title) {
        jFrame.setBounds(0, 0, (int) jFrame.getPreferredSize().getWidth() + 20, (int) jFrame.getPreferredSize().getHeight() + 50);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(resizeable);
        jFrame.setTitle(title);
    }
}
