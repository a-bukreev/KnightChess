/*
Second task
 */

package com.artyom;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;

public class KnightChessMain extends JPanel implements ActionListener {

    private static JFrame jFrame;
    private Image knight;
    private static final int SCALE = 65;
    private Timer timer = new Timer(100, this);

    //Размерность поля x*x
    static final int WIDTH = 8;
    static final int HEIGHT = 8;

    static int rx[] = new int[WIDTH*HEIGHT];
    static int ry[] = new int[WIDTH*HEIGHT];

    Knight k = new Knight();

    public KnightChessMain() {
        knight = Toolkit.getDefaultToolkit().createImage("knight.jpg");
        timer.start();
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH * SCALE + 5, HEIGHT * SCALE + 5);

        for (int x = 0; x < WIDTH * SCALE; x += SCALE) {
            Color c = new Color(0, 0, 0);
            g.setColor(c);
            g.drawLine(x, 0, x, HEIGHT * SCALE);
        }

        for (int y = 0; y < WIDTH * SCALE; y += SCALE) {
            Color c = new Color(0, 0, 0);
            g.setColor(c);
            g.drawLine(0, y, WIDTH * SCALE, y);
        }

        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            g.drawImage(knight, (rx[i] - 1) * SCALE + 5, (ry[i] - 1) * SCALE + 7, null);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 13));
            String position = Integer.toString(i);
            g.drawString(position, (rx[i] - 1) * SCALE + 50, (ry[i] - 1) * SCALE + 14);

            repaint();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter X coordinate: ");
        rx[0] = input.nextInt();
        System.out.println("Enter Y coordinate: ");
        ry[0] = input.nextInt();
        System.out.println("Start position: " + "(" + rx[0] + "," + ry[0] + ")");

        jFrame = new JFrame("KnightChess");
        jFrame.setSize(WIDTH * SCALE + 6, HEIGHT * SCALE + 28);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.add(new KnightChessMain());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            k.calculate();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        timer.stop();

        if (Knight.flagNoSolutions == 1) {
            JOptionPane.showMessageDialog(null, "Solution not found!");
            jFrame.setVisible(false);
            jFrame.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Solution found!");
            jFrame.setVisible(false);
            jFrame.setVisible(true);
        }
    }
}