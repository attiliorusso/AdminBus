
package Prenotazioni.Model.Beans;

import java.util.Date;

public class TitoloViaggio {
	public TitoloViaggio() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int ID;
	private Date dataOraEmissione;
	private double prezzo;
	private String localit‡Partenza;
	private String localit‡Arrivo;
	
	public TitoloViaggio(int ID, Date dataOraEmissione, double prezzo, String localit‡Partenza, String localit‡Arrivo) {
		this.ID = ID;
		this.dataOraEmissione = dataOraEmissione;
		this.prezzo = prezzo;
		this.localit‡Partenza = localit‡Partenza;
		this.localit‡Arrivo = localit‡Arrivo;
	}

	public int getID() {
		return ID;
	}

	public Date getDataOraEmissione() {
		return dataOraEmissione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public String getLocalit‡Partenza() {
		return localit‡Partenza;
	}

	public String getLocalit‡Arrivo() {
		return localit‡Arrivo;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setDataOraEmissione(Date dataOraEmissione) {
		this.dataOraEmissione = dataOraEmissione;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public void setLocalit‡Partenza(String localit‡Partenza) {
		this.localit‡Partenza = localit‡Partenza;
	}

	public void setLocalit‡Arrivo(String localit‡Arrivo) {
		this.localit‡Arrivo = localit‡Arrivo;
	}	

}

