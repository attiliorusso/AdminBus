package Prenotazioni.Model.Beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Prenotazioni.Model.Beans.Corsa;


public class Abbonamento extends TitoloViaggio {
	private List<Corsa> corse;
	private Date dataFine;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	
	public Abbonamento(int ID,
					   List<Corsa> corse,
					   Date dataFine,
					   Date dataOraEmissione,
					   String codiceFiscale,
					   String nome,
					   String cognome,
					   String luogoPartenza,
					   String luogoArrivo,
					   double prezzo) {
		super(ID, dataOraEmissione, prezzo, luogoPartenza, luogoArrivo);
		this.corse = corse;
		this.dataFine = dataFine;
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
	}

	public Abbonamento() {
		// TODO Auto-generated constructor stub
	}

	public List<Corsa> getCorse() {
		return corse;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCorse(List<Corsa> corse) {
		this.corse = corse;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void print() {
		System.out.println("_________Stampa abbonamento_________");
		System.out.println("ID titolo viaggio: " + this.getID());
		System.out.println("Nome: " + this.nome);
		System.out.println("Cognome: " + this.cognome);
		System.out.println("Codice Fiscale: " + this.codiceFiscale);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String dataS1 = sdf.format(this.getDataFine());
		System.out.println("Valido fino al: " + dataS1);
		System.out.println("Partenza: " + this.getLocalit‡Partenza());
		System.out.println("Arrivo: " + this.getLocalit‡Arrivo());
		String dataS2 = sdf.format(this.getDataOraEmissione());
		System.out.println("Data ora emissione: " + dataS2);
		System.out.println("Costo titolo viaggio: " + this.getPrezzo() + " Ä");
		
	}
	
}

