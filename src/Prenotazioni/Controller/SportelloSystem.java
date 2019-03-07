package Prenotazioni.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Prenotazioni.Model.Beans.Abbonamento;
import Prenotazioni.Model.Beans.Biglietto;
import Prenotazioni.Model.Beans.Cassa;
import Prenotazioni.Model.Beans.Contanti;
import Prenotazioni.Model.Beans.Corsa;
import Prenotazioni.Model.Beans.TitoloViaggio;
import Prenotazioni.Model.Beans.Tratta;
import Prenotazioni.Model.DB.DAOFactory;

public class SportelloSystem {
	
	private static SportelloSystem instance;
	private Cassa cassa = null;
	private double sommaInserita;
	private String partenza;
	private String arrivo;
	private List<Corsa> listaCorseAbbonamento;
	private Corsa corsaSelezionata;
	private double costoBiglietto;
	
	private SportelloSystem() {	
		cassa = DAOFactory.getDAOCassa().leggiCassa();
		corsaSelezionata = new Corsa();
		listaCorseAbbonamento = new ArrayList<Corsa>();
		costoBiglietto = 0;
	}

	public static SportelloSystem getInstance() {
		if(instance == null) {
			instance = new SportelloSystem();
		}
		return instance;
	}
	
	
	public Cassa getCassa() {
		return cassa;
	}
	
	public void setCassa(Cassa cassa) {
		this.cassa = cassa;
	}
	
	public double getsommaInserita() {
		return sommaInserita;
	}

	public void setsommaInserita(double sommaInserita) {
		this.sommaInserita = sommaInserita;
	}
	
	public Corsa getCorsaSelezionata() {
		return corsaSelezionata;
	}
	
	public void setCorsaSelezionata(Corsa corsaSelezionata) {
		this.corsaSelezionata = corsaSelezionata;
	}

	public String getPartenza() {
		return partenza;
	}

	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}

	public String getArrivo() {
		return arrivo;
	}

	public void setArrivo(String arrivo) {
		this.arrivo = arrivo;
	}


	public List<Corsa> getCorseAbbonamento() {
		return listaCorseAbbonamento;
	}

	public void setCorseAbbonamento(List<Corsa> listaCorse) {
		this.listaCorseAbbonamento = listaCorse;
	} 

	public double getCostoBiglietto() {
		return costoBiglietto;
	}

	public void setCostoBiglietto(double costoBiglietto) {
		this.costoBiglietto = costoBiglietto;
	}

	public List<Corsa> elencaCorse(String partenza, String arrivo) {
		this.partenza = partenza;
		this.arrivo = arrivo;
		List<Corsa> listaCorse = DAOFactory.getDAOCorsa().doRetrieveByPlace(partenza, arrivo);
		return listaCorse;					
	}

	public Corsa prenotaBiglietto(int idCorsa) {
		Corsa corsa = DAOFactory.getDAOCorsa().doRetrieveByKey(idCorsa);
		corsaSelezionata = corsa;
		return corsa;
	}
	
	public boolean verificaDisponibilit‡Abbonamento(Corsa corsa){
		List<Corsa> corseTotali = DAOFactory.getDAOCorsa().doRetrieveByPlace(partenza, arrivo);
		List<Corsa> corseMese = new ArrayList<Corsa>();
		for(Corsa c1:corseTotali) {
			if((c1.getGiorni() - corsa.getGiorni()) <= 30 && (c1.getGiorni() - corsa.getGiorni()) >= 0)
				corseMese.add(c1);
		}
		List<Corsa> corseLibereMese = new ArrayList<Corsa>();
		for(Corsa c2:corseMese) {
			if(c2.getPostiDisponibili() > 0)
				corseLibereMese.add(c2);
		}
		
		if(corseLibereMese.size() == corseMese.size()) {
			setCorseAbbonamento(corseLibereMese);
			return true;
		}
		else 
			return false;
		
	}
	
	
	public double calcolaPrezzoBiglietto(String from, String to) {
		Tratta tratta = DAOFactory.getDAOTratta().doRetrieveByKey(from, to);
		double prezzo = tratta.getPrezzo();
		costoBiglietto = prezzo;
		
		return prezzo;
	}
	
	public double calcolaPrezzoAbbonamento(double prezzoBiglietto) {
		double prezzoAbbonamento = listaCorseAbbonamento.size() * prezzoBiglietto;
		return prezzoAbbonamento;
	}
	
	
	public boolean verificaMaterialeStampa() {
		boolean verifica = false;
		double random = Math.random() * 10;
		if(random > 0)
			verifica = true;
		return verifica;
	}
	
	public void annullaOperazione() {
		partenza = "";
		arrivo = "";
		listaCorseAbbonamento = null;
		corsaSelezionata = null;
		sommaInserita = 0;
		costoBiglietto = 0;
	}

	public double calcolaResto(double sommaInserita, double prezzo) {
		double resto = sommaInserita - prezzo;
		return resto;
	}

	public boolean verificaDisponibilit‡Resto(double resto) {
		boolean verifica = false;
		double random = Math.random() * 10;
		if(random > 0 && resto < cassa.getDisponibilit‡())
			verifica = true;
		return verifica;
	}
	
	public Biglietto creaBiglietto() {
		List<Biglietto> bigliettiEmessi = DAOFactory.getDAOBiglietto().doRetrieveAll();
		Biglietto bigliettoNuovo = new Biglietto();
		int count = bigliettiEmessi.size() + 1;
		bigliettoNuovo.setID(count);
		bigliettoNuovo.setCorsa(corsaSelezionata);
		Date dataSistema = new Date();
		bigliettoNuovo.setDataOraEmissione(dataSistema);
		bigliettoNuovo.setLocalit‡Partenza(partenza);
		bigliettoNuovo.setLocalit‡Arrivo(arrivo);
		bigliettoNuovo.setPrezzo(costoBiglietto);
		
		return bigliettoNuovo;
	}
	
	public Abbonamento creaAbbonamento(Corsa corsa) {
		List<Abbonamento> abbonamentiEmessi = DAOFactory.getDAOAbbonamento().doRetrieveAll();
		Abbonamento abbonamentoNuovo = new Abbonamento();
		int count = abbonamentiEmessi.size() + 1;
		abbonamentoNuovo.setID(count);
		abbonamentoNuovo.setCorse(listaCorseAbbonamento);
		int giorniAggiunti = corsa.getDataOra().getDate() + 30;
		Date dataFine = corsa.getDataOra();
		dataFine.setDate(giorniAggiunti);
		abbonamentoNuovo.setDataFine(dataFine);
		Date dataSistema = new Date();
		abbonamentoNuovo.setDataOraEmissione(dataSistema);
		abbonamentoNuovo.setLocalit‡Partenza(partenza);
		abbonamentoNuovo.setLocalit‡Arrivo(arrivo);
		abbonamentoNuovo.setPrezzo(calcolaPrezzoAbbonamento(costoBiglietto));
		
		return abbonamentoNuovo;
	}
	
	public Contanti creaPagamentoContanti(TitoloViaggio t) {
		Contanti pagamentoNuovo = new Contanti();
		pagamentoNuovo.setsommaInserita(sommaInserita);
		if(t instanceof Biglietto) {
			pagamentoNuovo.setResto(calcolaResto(sommaInserita, t.getPrezzo()));
		}
		else 
			pagamentoNuovo.setResto(calcolaResto(sommaInserita, calcolaPrezzoAbbonamento(t.getPrezzo())));
		pagamentoNuovo.setTitoloViaggio(t);
		
		return pagamentoNuovo;
	}
	
	public void acquistaBiglietto(Biglietto b) {
		if(corsaSelezionata.getPostiDisponibili() == 0)
			System.out.println("Biglietto non acquistabile per mancanza posti.");
		else{
			cassa.setDisponibilit‡(cassa.getDisponibilit‡() + b.getPrezzo());
	   		if(b != null)
				DAOFactory.getDAOBiglietto().saveOrUpdate(b);
			Contanti c = creaPagamentoContanti(b);
			if(c != null)
				DAOFactory.getDAOContanti().saveOrUpdate(c);
			DAOFactory.getDAOCorsa().saveOrUpdate(corsaSelezionata);
			b.print();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			System.out.println("Valido per " + (int)(b.getPrezzo() / calcolaPrezzoBiglietto(partenza, arrivo)) + " persone");
			System.out.println("ID corsa: " + b.getCorsa().getIDCorsa() + " - " + b.getCorsa().getLinea().getNome() + " - " + sdf.format(b.getCorsa().getDataOra()));
			System.out.println("Resto emesso: " + c.getResto() + " Ä");
		}
	}
	
	public void acquistaAbbonamento(Abbonamento a) {
		cassa.setDisponibilit‡(cassa.getDisponibilit‡() + a.getPrezzo());	
		for(Corsa c:listaCorseAbbonamento) {
			DAOFactory.getDAOCorsa().saveOrUpdate(c);
		}
		
		if(a != null) {
			DAOFactory.getDAOAbbonamento().saveOrUpdate(a);
			DAOFactory.getDAOAbbonamento().saveOrUpdate(a, listaCorseAbbonamento);
		}
		Contanti c = creaPagamentoContanti(a);
		if(c != null)
			DAOFactory.getDAOContanti().saveOrUpdate(c);
		a.print();
		System.out.print("Lista corse per abbonamento id = " + a.getID() + ": ");
		for(Corsa c2:listaCorseAbbonamento) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			System.out.println(" [ " + c2.getIDCorsa() + ", " + c2.getLinea().getNome() + ", " + sdf.format(c2.getDataOra()) + " ] ");
		}
		System.out.println("Resto emesso: " + c.getResto() + "Ä");
	}
	
	public void acquistaTitoloViaggio(TitoloViaggio t) {
		if(t instanceof Biglietto)
			this.acquistaBiglietto((Biglietto) t);
		else
			this.acquistaAbbonamento((Abbonamento) t);
		DAOFactory.getDAOCassa().aggiornaCassa(cassa);
	}
	
}
