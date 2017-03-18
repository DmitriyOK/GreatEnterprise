package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dmitriy on 17.03.2017.
 */
public class AppView {

    static JFrame frame;

//    public static void main(String[] args) {
//
//        }


    static void displayJFrame() {
        frame = new JFrame("Рабочее время");
        JButton showDialogButton = new JButton("авторизация");
        showDialogButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            }
        });


        frame.getContentPane().setLayout(new FlowLayout());
        frame.add(showDialogButton);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
