package implementation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import interfaces.FanSpeed;

public class ControlDisplay extends JPanel {

	private JFrame frame;
	private String text;
	private ControlUnit controlUnit;
	private GridBagLayout layout;
	private GridBagConstraints c;
	private JButton startButton, stopButton, lowSpeedButton, mediumSpeedButton, highSpeedButton, incSpeedButton,
			decSpeedButton;

	public ControlDisplay(ControlUnit controlUnit) {
		this.controlUnit = controlUnit;
		frame = new JFrame("ControlFrame");
		layout = new GridBagLayout();
		c = new GridBagConstraints();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(300, 600));
		this.setLayout(layout);
		createStartButton();
		createStopButton();
		createLowSpeedButton();
		createMediumSpeedButton();
		createHighSpeedButton();
		createIncSpeedButton();
		createDecSpeedButton();
		frame.add(this);
		frame.setVisible(true);
	}

	public void createStartButton() {
		startButton = new JButton("start");
		c.gridx = 0;
		c.gridy = 0;
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlUnit.start();
				/*
				 * if (started) { JOptionPane.showMessageDialog(null, "Ready!");
				 * } else { JOptionPane.showMessageDialog(null,
				 * "Select speed!"); }
				 */
			}
		});
		this.add(startButton, c);
	}

	public void createStopButton() {
		stopButton = new JButton("stop");
		c.gridx = 1;
		c.gridy = 0;
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlUnit.stop();
			}
		});
		this.add(stopButton, c);
	}

	public void createLowSpeedButton() {
		lowSpeedButton = new JButton("lowSpeed");
		c.gridx = 0;
		c.gridy = 1;
		lowSpeedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlUnit.setFanSpeed(FanSpeed.valueOf("LOWSPEED"));
			}
		});
		this.add(lowSpeedButton, c);
	}

	public void createMediumSpeedButton() {
		mediumSpeedButton = new JButton("mediumSpeed");
		c.gridx = 1;
		c.gridy = 1;
		mediumSpeedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlUnit.setFanSpeed(FanSpeed.valueOf("MEDIUMSPEED"));
			}
		});
		this.add(mediumSpeedButton, c);
	}

	public void createHighSpeedButton() {
		highSpeedButton = new JButton("highSpeed");
		c.gridx = 2;
		c.gridy = 1;
		highSpeedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlUnit.setFanSpeed(FanSpeed.valueOf("HIGHSPEED"));
			}
		});
		this.add(highSpeedButton, c);
	}

	public void createIncSpeedButton() {
		incSpeedButton = new JButton("incSpeed");
		c.gridx = 0;
		c.gridy = 2;
		incSpeedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlUnit.incSpeed();
			}
		});
		this.add(incSpeedButton, c);
	}

	public void createDecSpeedButton() {
		decSpeedButton = new JButton("decSpeed");
		c.gridx = 1;
		c.gridy = 2;
		decSpeedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlUnit.decSpeed();
			}
		});
		this.add(decSpeedButton, c);
	}
}
