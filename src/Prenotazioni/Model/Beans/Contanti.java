package Prenotazioni.Model.Beans;

import java.util.Date;

import Prenotazioni.Model.Beans.Pagamento;

public class Contanti extends Pagamento {
	private double resto;

	public Contanti(double sommaInserita, Date dataOraEmissione, TitoloViaggio titoloViaggio, double resto) {
		super(sommaInserita, dataOraEmissione, titoloViaggio);
		this.resto = resto;
	}

	public Contanti() {
		// TODO Auto-generated constructor stub
	}

	public double getResto() {
		return resto;
	}

	public void setResto(double resto) {
		this.resto = resto;
	}

	@Override
	public String toString() {
		return "Contanti [resto=" + resto + ", sommaInserita=" + getsommaInserita() + "]";
	}
	
}
