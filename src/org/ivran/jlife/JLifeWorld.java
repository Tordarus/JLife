package org.ivran.jlife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JComponent;

public class JLifeWorld extends JComponent
{
	private static final long serialVersionUID = -8788608530317688013L;

	private boolean[][]	currentGeneration;
	private boolean[][]	nextGeneration;
	
	private int			gridWidth;
	private int			gridHeight;
	private int			cellSize;
	
	private Random		random;
	
	private JLifeWorld()
	{
		random = new Random();
	}
	
	protected JLifeWorld(int gridWidth, int gridHeight, int cellSize)
	{
		this();
		
		setGridWidth(gridWidth);
		setGridHeight(gridHeight);
		setCellSize(cellSize);
		
		this.currentGeneration = new boolean[gridWidth][gridHeight];
		this.nextGeneration = new boolean[gridWidth][gridHeight];
		
		int width = gridWidth * cellSize;
		int height = gridHeight * cellSize;
		
		Dimension size = new Dimension(width, height);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		setSize(size);
		
		fillRandomly();
	}
	
	public void setGridWidth(int gridWidth)
	{
		if(gridWidth <= 0)
		{
			throw new IllegalArgumentException("gridWidth must be above 0");
		}
		this.gridWidth = gridWidth;
	}
	
	public void setGridHeight(int gridHeight)
	{
		if(gridHeight <= 0)
		{
			throw new IllegalArgumentException("gridHeight must be above 0");
		}
		this.gridHeight = gridHeight;
	}
	
	public int getGridWidth()
	{
		return gridWidth;
	}
	
	public int getGridHeight()
	{
		return gridHeight;
	}
	
	public int getCellSize()
	{
		return cellSize;
	}
	
	public void setCellSize(int cellSize)
	{
		if(cellSize <= 0)
		{
			throw new IllegalArgumentException("cellSize must be > 0");
		}
		this.cellSize = cellSize;
	}
	
	public boolean isInWorld(int x, int y)
	{
		return (x > 0 && x < gridWidth) && (y > 0 && y < gridHeight);
	}
	
	public boolean isAlive(int x, int y)
	{
		return isInWorld(x, y) && currentGeneration[x][y];
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.black);
		
		for(int x = 0; x < gridWidth; x++)
		{
			for(int y = 0; y < gridHeight; y++)
			{
				if(!isAlive(x, y))
				{
					continue;
				}
				g2d.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
			}
		}
		
		g2d.dispose();
	}
	
	public void fillRandomly()
	{
		for(int x = 0; x < gridWidth; x++)
		{
			for(int y = 0; y < gridHeight; y++)
			{
				currentGeneration[x][y] = random.nextBoolean();
			}
		}
		repaint();
	}
	
	public void tick()
	{
		for(int x = 0; x < gridWidth; x++)
		{
			for(int y = 0; y < gridHeight; y++)
			{
				tickCell(x, y);
			}
		}
		shift();
		repaint();
	}
	
	private void tickCell(int x, int y)
	{
		int livingNeighbors = countLivingNeighbors(x, y);
		
		if(isAlive(x, y))
		{
			if(livingNeighbors < 2 || livingNeighbors > 3)
			{
				nextGeneration[x][y] = false;
			}
		}
		else
		{
			if(livingNeighbors == 3)
			{
				nextGeneration[x][y] = true;
			}
		}
	}
	
	private int countLivingNeighbors(int x, int y)
	{
		int livingNeighbors = 0;

		for(int xx = x - 1; xx < x + 2; xx++)
		{
			for(int yy = y - 1; yy < y + 2; yy++)
			{
				if(xx == x && yy == y)
				{
					continue;
				}
				
				if(isAlive(xx, yy))
				{
					livingNeighbors++;
				}
			}
		}
		
		return livingNeighbors;
	}
	
	private void shift()
	{
		for(int x = 0; x < gridWidth; x++)
		{
			for(int y = 0; y < gridHeight; y++)
			{
				currentGeneration[x][y] = nextGeneration[x][y];
			}
		}
	}
}
