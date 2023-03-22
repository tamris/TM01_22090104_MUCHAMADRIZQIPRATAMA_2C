import View.myGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        myGUI content = new myGUI();
        frame.setContentPane(content.getMainPanel());
        content.getMinimize().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setExtendedState(frame.getExtendedState()|frame.ICONIFIED);
            }
        });

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
    }
}
