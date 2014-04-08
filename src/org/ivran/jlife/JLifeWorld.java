package org.ivran.jlife;

public class JLifeWorld {

  private final boolean[][] currentGeneration;
  private final boolean[][] nextGeneration;

  private final int width;
  private final int height;

  public JLifeWorld(int width, int height) {
    if (width <= 0) {
      throw new IllegalArgumentException("width must be above 0");
    }

    if (height <= 0) {
      throw new IllegalArgumentException("height must be above 0");
    }

    this.currentGeneration = new boolean[width][height];
    this.nextGeneration = new boolean[width][height];

    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public boolean isAlive(int x, int y) {
    return currentGeneration[x][y];
  }

  public void setAlive(int x, int y, boolean alive) {
    this.currentGeneration[x][y] = alive;
  }

  public void update() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        updateCell(x, y);
      }
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        currentGeneration[x][y] = nextGeneration[x][y];
      }
    }
  }

  private void updateCell(int x, int y) {
    int livingNeighbors = getAliveNeighbours(x, y);

    if (isAlive(x, y)) {
      if (livingNeighbors < 2 || livingNeighbors > 3) {
        nextGeneration[x][y] = false;
      }
    }
    else {
      if (livingNeighbors == 3) {
        nextGeneration[x][y] = true;
      }
    }
  }

  private int getAliveNeighbours(int x, int y) {
    int aliveNeighbours = 0;

    for (int xx = x - 1; xx < x + 2; xx++) {
      for (int yy = y - 1; yy < y + 2; yy++) {
        if (xx == x && yy == y) {
          continue;
        }

        if (isAlive(xx, yy)) {
          aliveNeighbours++;
        }
      }
    }

    return aliveNeighbours;
  }

}
