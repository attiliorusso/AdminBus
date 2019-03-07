package Prenotazioni.Model.Beans;

public class Tratta {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Localit‡Arrivo == null) ? 0 : Localit‡Arrivo.hashCode());
		result = prime * result + durata;
		result = prime * result + ((localit‡Partenza == null) ? 0 : localit‡Partenza.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prezzo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tratta other = (Tratta) obj;
		if (Localit‡Arrivo == null) {
			if (other.Localit‡Arrivo != null)
				return false;
		} else if (!Localit‡Arrivo.equals(other.Localit‡Arrivo))
			return false;
		if (durata != other.durata)
			return false;
		if (localit‡Partenza == null) {
			if (other.localit‡Partenza != null)
				return false;
		} else if (!localit‡Partenza.equals(other.localit‡Partenza))
			return false;
		if (Double.doubleToLongBits(prezzo) != Double.doubleToLongBits(other.prezzo))
			return false;
		return true;
	}

	private Localit‡ localit‡Partenza;
	private Localit‡ Localit‡Arrivo;
	private int durata;
	private double prezzo;
	
	public Tratta(Localit‡ localit‡Partenza,
			      Localit‡ localit‡Arrivo,
			      int durata,
			      double prezzo) {
		this.localit‡Partenza = localit‡Partenza;
		this.Localit‡Arrivo = localit‡Arrivo;
		this.durata = durata;
		this.prezzo = prezzo;
	}

	public Localit‡ getLocalit‡Partenza() {
		return localit‡Partenza;
	}

	public Localit‡ getLocalit‡Arrivo() {
		return Localit‡Arrivo;
	}

	public int getDurata() {
		return durata;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setLocalit‡Partenza(Localit‡ localit‡Partenza) {
		this.localit‡Partenza = localit‡Partenza;
	}

	public void setLocalit‡Arrivo(Localit‡ localit‡Arrivo) {
		Localit‡Arrivo = localit‡Arrivo;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public String toString() {
		return "Tratta [localit‡Partenza=" + localit‡Partenza + ", Localit‡Arrivo=" + Localit‡Arrivo + "]";
	}

	
}

