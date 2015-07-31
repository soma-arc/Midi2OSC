package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import midi.MidiHandler;

public class ControlPanel extends JPanel {
	public ControlPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setUI();
	}

	private JTextField addressField;
	private JSpinner portSpinner;
	private MidiHandler midiHandler = null;
	private JLabel stateLabel;
	private void setUI(){
		HorizontalPanel addressPanel = new HorizontalPanel();
		addressField = new JTextField();
		addressField.setMaximumSize(new Dimension(80, 40));
		addressField.setText("127.0.0.1");
		addressPanel.add(new JLabel("アドレス"));
		addressPanel.add(addressField);
		
		HorizontalPanel portPanel = new HorizontalPanel();
		portSpinner = new JSpinner(new SpinnerNumberModel(9001, 1024, null, 1));
		portSpinner.setMaximumSize(new Dimension(80, 40));
		portPanel.add(new JLabel("ポート番号"));
		portPanel.add(portSpinner);
		
		JButton startButton = new JButton("start");
		startButton.setAlignmentX(CENTER_ALIGNMENT);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(midiHandler != null) return;
				midiHandler = new MidiHandler(addressField.getText(), (int) portSpinner.getValue());
				stateLabel.setText("state : listen");
			}
		});
		JButton stopButton = new JButton("stop");
		stopButton.setAlignmentX(CENTER_ALIGNMENT);
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(midiHandler == null) return;
				midiHandler.close();
				midiHandler = null;
				stateLabel.setText("state : stop");
			}
		});
		stateLabel = new JLabel("state : ");
		
		add(addressPanel);
		add(portPanel);
		add(startButton);
		add(stopButton);
		add(stateLabel);
	}
}
