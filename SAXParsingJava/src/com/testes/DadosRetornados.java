package com.testes;

import java.util.ArrayList;

public class DadosRetornados {

	private String salary;
	
	@MyList(nome="lista-01", classRetorno=Person.class)
	private ArrayList<Person> lista01;

	@MyList(nome="lista-02", classRetorno=Person.class)
	private ArrayList<Person> lista02;

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public ArrayList<Person> getLista01() {
		return lista01;
	}

	public void setLista01(ArrayList<Person> lista01) {
		this.lista01 = lista01;
	}

	public ArrayList<Person> getLista02() {
		return lista02;
	}

	public void setLista02(ArrayList<Person> lista02) {
		this.lista02 = lista02;
	}
	
}
