package org.ivran.jlife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class JLifeView extends JComponent {

  private static final long serialVersionUID = -8788608530317688013L;

  private final JLifeWorld world;
  private int cellSize;

  public JLifeView(JLifeWorld world, int cellSize) {
    this.world = world;
    this.cellSize = cellSize;

    Dimension size = new Dimension(world.getWidth() * cellSize, world.getHeight() * cellSize);
    setMinimumSize(size);
    setPreferredSize(size);
    setMaximumSize(size);
    setSize(size);
  }

  public int getCellSize() {
    return cellSize;
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.white);
    g2d.fillRect(0, 0, getWidth(), getHeight());
    g2d.setColor(Color.black);

    for (int x = 0; x < world.getWidth(); x++) {
      for (int y = 0; y < world.getHeight(); y++) {
        if (!world.isAlive(x, y)) {
          continue;
        }

        g2d.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
      }
    }

    g2d.dispose();
  }

}
