package launcher;

import java.awt.Dimension;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;

import ui.ControlPanel;
import midi.MidiHandler;

public class Launcher {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Midi2OSC");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(new ControlPanel());
		frame.setSize(new Dimension(560, 560));
		frame.setVisible(true);
//		new MidiHandler("127.0.0.1", 9001);
	}

}
