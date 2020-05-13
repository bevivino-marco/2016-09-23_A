package it.polito.tdp.gestionale.model;

import java.util.ArrayList;
import java.util.List;

public class Corso extends Nodo {

	private List<Studente> studenti;
	private String codins;
	private String nome;
	private int crediti;
	private int pd;

	public Corso() {
		super();

	}

	public Corso(String codins) {
		super();
		this.codins = codins;
	}

	public Corso(String codins, int crediti, String nome, int pd) {
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}

	/*
	 * Getters and Setters
	 */
	public String getCodins() {
		if (codins == null)
			return "";
		return codins;
	}

	public void setCodins(String codins) {
		this.codins = codins;
	}

	public String getNome() {
		if (nome == null)
			return "";
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}

	public int getPd() {
		return pd;
	}

	public void setPd(int pd) {
		this.pd = pd;
	}

	public List<Studente> getStudenti() {
		if (studenti == null) {
			return new ArrayList<Studente>();
		}
		return studenti;
	}

	public void setStudenti(List<Studente> studenti) {
		this.studenti = studenti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s\n", codins);
	}
	
}
