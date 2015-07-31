package midi;

import java.util.ArrayList;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;

import osc.OSCSender;

public class MidiHandler {
	private OSCSender sender;
	private ArrayList<MidiDevice> openedDevices = new ArrayList<>();

	public MidiHandler(String addressName, int port){
		MidiDevice device;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		sender = new OSCSender(addressName, port);
		for (int i = 0; i < infos.length; i++) {
			try {
				device = MidiSystem.getMidiDevice(infos[i]);
				System.out.println(infos[i] +" -- "+ infos[i].getDescription());
				java.util.List<Transmitter> transmitters = device.getTransmitters();
				for(int j = 0; j<transmitters.size();j++) {
					transmitters.get(j).setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString(), sender));
				}

				Transmitter trans = device.getTransmitter();
				trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString(), sender));

				device.open();
				openedDevices.add(device);
				System.out.println(device.getDeviceInfo()+" Was Opened");
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(){
		sender.close();
		for(MidiDevice device : openedDevices){
			device.close();
		}
	}
}
