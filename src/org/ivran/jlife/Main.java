package org.ivran.jlife;

import javax.swing.JFrame;

public class Main {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Game Of Life");
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new JLife());
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

}
