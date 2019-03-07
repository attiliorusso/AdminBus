package Prenotazioni.Model.Beans;

import java.util.List;
import java.util.Set;

public class Linea {
	private String nome;
	private Localit‡ localit‡Partenza;
	private Localit‡ localit‡Arrivo;
	private Set<Tratta> tratte;

	public Linea(String nome, Localit‡ localit‡Partenza, Localit‡ localit‡Arrivo, Set<Tratta> tratte) {
		this.nome = nome;
		this.localit‡Partenza = localit‡Partenza;
		this.localit‡Arrivo = localit‡Arrivo;
		this.tratte = tratte;
	}

	public String getNome() {
		return nome;
	}

	public Localit‡ getLocalit‡Partenza() {
		return localit‡Partenza;
	}

	public Localit‡ getLocalit‡Arrivo() {
		return localit‡Arrivo;
	}

	public Set<Tratta> getTratte() {
		return tratte;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLocalit‡Partenza(Localit‡ localit‡Partenza) {
		this.localit‡Partenza = localit‡Partenza;
	}

	public void setLocalit‡Arrivo(Localit‡ localit‡Arrivo) {
		this.localit‡Arrivo = localit‡Arrivo;
	}

	public void setTratte(Set<Tratta> tratte) {
		this.tratte = tratte;
	}

	
}
