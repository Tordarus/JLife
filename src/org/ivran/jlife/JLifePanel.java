package org.ivran.jlife;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class JLifePanel extends JPanel implements ActionListener {

  private static final long serialVersionUID = 3180925303456102442L;

  private final Timer timer;
  private final JLifeWorld world;
  private final JLifeView worldView;

  public JLifePanel() {
    this(new JLifeWorld(200, 200));
  }

  public JLifePanel(JLifeWorld world) {
    timer = new Timer(1000, this);
    this.world = world;
    this.worldView = new JLifeView(world, 2);

    setLayout(new BorderLayout());
    add(new JLifeControls(this), BorderLayout.SOUTH);
    add(worldView, BorderLayout.CENTER);
  }

  public JLifeWorld getWorld() {
    return world;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    world.update();
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
