package org.ivran.jlife;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JLifeControls extends JPanel {

  private static final long serialVersionUID = -1543360357936435971L;

  private final ResourceBundle bundle;
  private final JLifePanel lifePanel;

  protected JLifeControls(JLifePanel lifePanel) {
    this.lifePanel = lifePanel;
    bundle = ResourceBundle.getBundle("ControlsBundle");

    add(createStartStopButton());
    add(createFillRandomlyButton());
    add(createSpeedSlider());

    setLayout(new FlowLayout(FlowLayout.LEFT));
  }

  private JButton createStartStopButton() {
    final JButton startStopButton = new JButton();
    startStopButton.setText(bundle.getString("button.start"));
    startStopButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent event) {
        if (lifePanel.isRunning()) {
          lifePanel.stop();
          startStopButton.setText(bundle.getString("button.start"));
        }
        else {
          lifePanel.start();
          startStopButton.setText(bundle.getString("button.stop"));
        }
      }
    });
    return startStopButton;
  }

  private JButton createFillRandomlyButton() {
    JButton fillRandomlyButton = new JButton();
    fillRandomlyButton.setText(bundle.getString("button.fill-random"));
    fillRandomlyButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent event) {
        lifePanel.getWorld();
      }
    });
    return fillRandomlyButton;
  }

  private JSlider createSpeedSlider() {
    DefaultBoundedRangeModel model = new DefaultBoundedRangeModel();
    model.setMinimum(1);
    model.setMaximum(1000);
    model.setValue(1000);

    final JSlider speedSlider = new JSlider();
    speedSlider.setModel(model);
    speedSlider.addChangeListener(new ChangeListener() {

      @Override
      public void stateChanged(ChangeEvent event) {
        lifePanel.setSpeed(speedSlider.getValue());
      }
    });
    return speedSlider;
  }

}
