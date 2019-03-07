package Prenotazioni.Model.Beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import Prenotazioni.Model.Beans.Corsa;

public class Biglietto extends TitoloViaggio {
	private Corsa corsa;
	
	public Biglietto(int ID,
			         Corsa corsa,
			         Date dataOraEmissione,
			         String luogoPartenza,
			         String luogoArrivo,
			         double prezzo) {
		super(ID, dataOraEmissione, prezzo, luogoPartenza, luogoArrivo);
		this.corsa = corsa;
	}

	public Biglietto() {
		// TODO Auto-generated constructor stub
	}

	public Corsa getCorsa() {
		return corsa;
	}

	public void setCorsa(Corsa corsa) {
		this.corsa = corsa;
	}
	
	public void print() {
		System.out.println("_________Stampa biglietto_________");
		System.out.println("ID titolo viaggio: " + this.getID());
		System.out.println("Partenza: " + this.getLocalit‡Partenza());
		System.out.println("Arrivo: " + this.getLocalit‡Arrivo());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String dataS = sdf.format(this.getDataOraEmissione());
		System.out.println("Data ora emissione: " + dataS);
		System.out.println("Costo titolo viaggio: " + this.getPrezzo() + " Ä");
	}
}