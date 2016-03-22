package ar.com.javacuriosities.networking.udp.streaming;

import javax.sound.sampled.AudioFormat;

public class AudioFormatHelper {

	// El AudioFormat contiene la información sobre el audio para interpretar los datos binarios
	public static AudioFormat getAudioFormat() {
		/* 
		 * Indica el numero de muestras por segundos, frecuencias típicas 
		 * 8000 (Teléfonos)
		 * 22050 (Radios)
		 * 32000 (Video miniDV)
		 * 44100 (CD) 
		 */
		float sampleRate = 8000.0F;
		
		// Indica el numero de bits en cada sample (8 o 16)
		int sampleSizeInBits = 16;
		
		// Indica si es canal Mono o Stereo
		int channel = 1;
		
		// Indica si los datos tiene signo o no
		boolean signed = true;
		
		// Indica si los datos están almacenados como bigEndian
		boolean bigEndian = false;
		
		return new AudioFormat(sampleRate, sampleSizeInBits, channel, signed, bigEndian);
	}
}