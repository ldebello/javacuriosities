package ar.com.javacuriosities.networking.udp.streaming;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;

/*
 * Este es un ejemplo de envío de streaming de audio por medio de una conexión UDP, en general
 * si queremos hacer algo mas real usaremos el protocolo RTSP (Real Time Streaming Protocol), el cual
 * suele utilizar UDP para el envío de audio o video y TCP para el envío de datos de control, porque es
 * importante recordar que UDP no asegura envío de paquetes ni el orden.
 * 
 * Utilizaremos el Java Sound API, esta API define varios dispositivos (mixers), usualmente al menos tendremos
 * uno de entrada y otro de salida
 */
public class AudioSender {

	private static final int PORT = 8888;

	public static volatile boolean isRunning = true;

	public static void main(String[] args) {
		// Pedimos los mixers de nuestro sistema
		Mixer.Info[] mixersInfo = AudioSystem.getMixerInfo();
		
		for (Mixer.Info info : mixersInfo) {
			System.out.println("Mixer: " + info);
		}

		// Los Line están asociados a los mixers, aca verificamos si el micrófono esta asociado a un mixer 
		if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
			
			// Creamos el socket de envío en cualquier puerto
			try (DatagramSocket socket = new DatagramSocket()) {
				
				// Configuramos el destino del audio
				InetAddress address = InetAddress.getLocalHost();
				
				// Creamos un DataLine el cual agrega funcionalidades sobre el Line (Métodos de transporte), el TargetDataLine permite leer audio
				DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, AudioFormatHelper.getAudioFormat());
				
				// Obtenemos la línea
				TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
				
				// Abrimos la línea
				targetDataLine.open(AudioFormatHelper.getAudioFormat());
				
				// El start permite que la línea empiece a usar I/O
				targetDataLine.start();

				while (isRunning) {
					// Creamos un buffer para leer los datos (Usamos 32000 porque el sample lo pusimos en 8000 y 2 bytes por sample = 16000 por segundo)
					byte[] buffer = new byte[32000];
					
					// Leemos y enviamos el audio
					targetDataLine.read(buffer, 0, buffer.length);
					sendAudio(socket, address, buffer);
				}
			} catch (Exception e) {
				// Log and Handle exception
				e.printStackTrace();
			}
		}
	}

	

	private static void sendAudio(DatagramSocket socket, InetAddress address, byte soundpacket[]) {
		try {
			socket.send(new DatagramPacket(soundpacket, soundpacket.length, address, PORT));
		} catch (IOException e) {
			// Log and Handle exception (Unable to send soundpacket using UDP)
			e.printStackTrace();
		}
	}
}