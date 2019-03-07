package Prenotazioni.Model.Beans;

import java.util.Date;

public class Pagamento {
	public Pagamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	private double sommaInserita;
	private Date dataOraEmissione;
	private TitoloViaggio titoloViaggio;
	
	public Pagamento(double sommaInserita, Date dataOraEmissione, TitoloViaggio titoloViaggio) {
		this.sommaInserita = sommaInserita;
		this.dataOraEmissione = dataOraEmissione;
		this.titoloViaggio = titoloViaggio;
	}

	public double getsommaInserita() {
		return sommaInserita;
	}

	public Date getDataOraEmissione() {
		return dataOraEmissione;
	}

	public TitoloViaggio getTitoloViaggio() {
		return titoloViaggio;
	}

	public void setsommaInserita(double sommaInserita) {
		this.sommaInserita = sommaInserita;
	}

	public void setDataOraEmissione(Date dataOraEmissione) {
		this.dataOraEmissione = dataOraEmissione;
	}

	public void setTitoloViaggio(TitoloViaggio titoloViaggio) {
		this.titoloViaggio = titoloViaggio;
	}

}