package Prenotazioni.Model.Beans;

public class Mezzo {
	private String targa;
	private int numeroTotalePosti;
	
	public Mezzo(String targa, int numeroTotalePosti) {
		this.targa = targa;
		this.numeroTotalePosti = numeroTotalePosti;
	}

	public String getTarga() {
		return targa;
	}

	public int getNumeroTotalePosti() {
		return numeroTotalePosti;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public void setNumeroTotalePosti(int numeroTotalePosti) {
		this.numeroTotalePosti = numeroTotalePosti;
	}

}
