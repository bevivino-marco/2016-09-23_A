package it.polito.tdp.gestionale.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Studente extends Nodo implements Comparable<Studente>{

	
	private List<Corso> corsi;
	private int matricola;
	private String cognome;
	private String nome;
	private String cds;

	public Studente(int matricola) {
		super ();
		this.matricola = matricola;
		corsi = new LinkedList<>();
	}

	public Studente(int matricola, String cognome, String nome, String cds) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cds = cds;
		corsi = new LinkedList<>();
	}

	/*
	 * Getters and Setters
	 */
	public int getMatricola() {
		return this.matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getCognome() {
		if (cognome == null)
			return "";
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		if (nome == null)
			return "";
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCds() {
		if (cds == null)
			return "";
		return cds;
	}

	public void setCds(String cds) {
		this.cds = cds;
	}

	public List<Corso> getCorsi() {
		if (corsi == null) {
			return new ArrayList<Corso>();
		}
		return corsi;
	}

	public void setCorsi(Corso c) {
		this.corsi.add(c);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + matricola;
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
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s\n", matricola, cognome, nome, cds);
	}


	@Override
	public int compareTo(Studente s) {
		return this.corsi.size()-s.getCorsi().size();
	}
	
}
