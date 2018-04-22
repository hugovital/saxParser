package com.testes;

public class ObjetoEmpilhado {
	
	private String nome;
	
	private Object objeto;
	
	private Class<?> clazz;
	
	private String nomeMetodo;
	
	private boolean list = false;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getNomeMetodo() {
		return nomeMetodo;
	}

	public void setNomeMetodo(String nomeMetodo) {
		this.nomeMetodo = nomeMetodo;
	}

	public boolean isList() {
		return list;
	}

	public void setList(boolean list) {
		this.list = list;
	}
	
}
