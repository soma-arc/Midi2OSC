package midi;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;


import osc.OSCSender;

public class MidiInputReceiver implements Receiver{
	private String name;
	private OSCSender sender;
	public MidiInputReceiver(String name, OSCSender sender) {
		this.name = name;
		this.sender = sender;
	}
	public void send(MidiMessage message, long timeStamp) {
		if (message instanceof ShortMessage) {
			ShortMessage sm = ((ShortMessage)message);
			switch(sm.getCommand()) {
			case ShortMessage.CONTROL_CHANGE:
				System.out.println(sm.getChannel());
				System.out.println(sm.getData1());
				System.out.println(sm.getData2());
				sender.send(sm.getChannel(), sm.getData1(), sm.getData2());
				break;
			}
		}
		System.out.println("midi received");
	}
	public void close() {}
}
