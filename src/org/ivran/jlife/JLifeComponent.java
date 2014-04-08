package org.ivran.jlife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JComponent;

public class JLifeComponent extends JComponent {

  private static final long serialVersionUID = -8788608530317688013L;

  private final JLifeWorld world;
  private final Random random;
  private int cellSize;

  public JLifeComponent(int worldWidth, int worldHeight, int cellSize) {
    world = new JLifeWorld(worldWidth, worldHeight);
    random = new Random();
    this.cellSize = cellSize;

    Dimension size = new Dimension(worldWidth * cellSize, worldHeight * cellSize);
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
        if (!world.isAlive(x, y))
          continue;

        g2d.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
      }
    }

    g2d.dispose();
  }

  public void fillRandomly() {
    for (int x = 0; x < world.getWidth(); x++) {
      for (int y = 0; y < world.getHeight(); y++) {
        world.setAlive(x, y, random.nextBoolean());
      }
    }
    repaint();
  }

  public void tick() {
    world.update();
    repaint();
  }

}
