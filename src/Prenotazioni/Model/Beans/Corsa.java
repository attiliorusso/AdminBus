package Prenotazioni.Model.Beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import Prenotazioni.Controller.SportelloSystem;
import Prenotazioni.Model.Beans.Mezzo;

public class Corsa {
	private int IDCorsa;
	private Linea linea;
	private Date dataOra;
	private int postiDisponibili;
	private Mezzo mezzo;
	
	public Corsa(int iDCorsa, Linea linea, Date dataOra, int postiDisponibili, Mezzo mezzo) {
		super();
		IDCorsa = iDCorsa;
		this.linea = linea;
		this.dataOra = dataOra;
		this.postiDisponibili = postiDisponibili;
		this.mezzo = mezzo;
	}

	public Corsa() {
		// TODO Auto-generated constructor stub
	}

	public int getIDCorsa() {
		return IDCorsa;
	}

	public void setIDCorsa(int iDCorsa) {
		IDCorsa = iDCorsa;
	}
	
	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public Date getDataOra() {
		return dataOra;
	}

	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}

	public int getPostiDisponibili() {
		return postiDisponibili;
	}

	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String dataS = sdf.format(dataOra);
		return "Corsa " + IDCorsa + " - " + linea.getNome() + " - data " + dataS + " - posti disponibili "
				+ postiDisponibili + " - mezzo " + mezzo.getTarga();
	}

	public int getGiorni() {
		long millisecondi = this.getDataOra().getTime();
		long sec = millisecondi / 1000;
		long min = sec / 60;
		long ore = min / 60;
		int giorni = (int) (ore / 24);
		return giorni;
	}
		
}