package ui;

import model.GameBackend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private GameBackend toPlay;
    JFrame frame;

    public GUI() {
        this.toPlay = new GameBackend();
        frame = new JFrame();

        JTextField response = new JTextField();
        response.setBounds(50,50, 150,20);

        JButton attackButton = new JButton("Attack");
        attackButton.setBounds(130,100,100, 40);
        attackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                response.setText("Huge Victory");
            }
        });

        JButton prestigeButton = new JButton("Prestige");
        prestigeButton.setBounds(200,100,100, 40);

        frame.add(attackButton);
        frame.add(prestigeButton);
        frame.add(response);

        frame.setSize(500,500);//400 width and 500 height
        frame.setLayout(null);
        frame.setVisible(true);
    }


}

