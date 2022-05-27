package liceocuneo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tastiera {

	/**
	 * legge un numero intero da tastiera
	 * @param messaggio messaggio da visualizzare a video prima dell'inserimento
	 * da tastiera
	 * @return
	 */
	public int leggiNumeroIntero(String messaggio) {
		int ris = 0;
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader buf = new BufferedReader(in);
		
		System.out.println(messaggio);
		String testo = null;
		try {
			testo = buf.readLine();
			if(testo != null) {
				ris = Integer.parseInt(testo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ris;
	}
}
