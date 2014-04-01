package org.ivran.jlife;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class JLife extends JPanel implements ActionListener {

  private static final long serialVersionUID = 3180925303456102442L;

  private Timer timer;
  private JLifeWorld world;

  public JLife() {
    setLayout(new BorderLayout());

    add(new JLifeControls(this), BorderLayout.SOUTH);
    add(world = new JLifeWorld(200, 200, 2), BorderLayout.CENTER);

    timer = new Timer(1000, this);
  }

  public void fillWorldRandomly() {
    world.fillRandomly();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    world.tick();
  }

  public void setSpeed(int speed) {
    timer.setDelay(speed);
  }

  public boolean isRunning() {
    return timer.isRunning();
  }

  public void start() {
    timer.start();
  }

  public void stop() {
    timer.stop();
  }

}
