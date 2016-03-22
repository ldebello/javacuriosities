package ar.com.javacuriosities.networking.udp.streaming;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class AudioReceiver {

	private static final int PORT = 8888;

	public static void main(String[] args) {
		Thread receiver = new AudioReceiverThread();
		receiver.start();
	}

	// Este thread es el encargado de recibir y reproducir el audio
	private static final class AudioReceiverThread extends Thread {
		public void run() {
			try (DatagramSocket socket = new DatagramSocket(PORT)){
				while (true) {
					byte buffer[] = new byte[32000];
					DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
					byte[] soundpacket = receiveAudio(socket, datagram);
					reproduce(soundpacket);
				}
			} catch (SocketException  e) {
				// Log and Handle exception
				e.printStackTrace();
			}
		}
		
		private static byte[] receiveAudio(DatagramSocket sock, DatagramPacket datagram) {
			try {
				sock.receive(datagram);
				return datagram.getData(); // soundpacket ;
			} catch (Exception e) {
				// Log and Handle exception
				e.printStackTrace();
				return null;
			}
		}
		
		private static void reproduce( byte soundbytes[]) {
			try {
				DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, AudioFormatHelper.getAudioFormat());
				
				// El source data line se usa para escribir datos en el
				SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
				sourceDataLine.open(AudioFormatHelper.getAudioFormat());
				sourceDataLine.start();
				
				sourceDataLine.write(soundbytes, 0, soundbytes.length);
				sourceDataLine.drain();
				sourceDataLine.close();
			} catch (Exception e) {
				// Log and Handle exception
				e.printStackTrace();
			}
		}
	}
}