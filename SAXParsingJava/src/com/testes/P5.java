package com.testes;

public class P5{

	@MyAnnotation(nome="controle", classRetorno=DadosControle.class)
	private DadosControle dadosControle;

	public DadosControle getDadosControle() {
		return dadosControle;
	}

	public void setDadosControle(DadosControle dadosControle) {
		this.dadosControle = dadosControle;
	}

	@MyAnnotation(nome="dados-de-negocio", classRetorno=DadosRetornados.class)
	private DadosRetornados retorno;

	public DadosRetornados getRetorno() {
		return retorno;
	}

	public void setRetorno(DadosRetornados retorno) {
		this.retorno = retorno;
	}
	


}
