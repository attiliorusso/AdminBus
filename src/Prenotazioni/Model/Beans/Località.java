package Prenotazioni.Model.Beans;

public class Localit� {
	private String nome;
	
	public Localit�(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	

}