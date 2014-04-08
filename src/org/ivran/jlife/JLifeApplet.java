package org.ivran.jlife;

import javax.swing.JApplet;

public class JLifeApplet extends JApplet {

  private static final long serialVersionUID = -537165024247170613L;

  private JLifePanel life;

  @Override
  public void init() {
    life = new JLifePanel();
  }

  @Override
  public void start() {
    add(life);
  }

}
