package com.testes;

public class AreaCanal {
	
	@MyAnnotation(nome="COD-CANA", classRetorno=Integer.class)
	private Integer codCanal;
	
	private String nomeCanal;

	public String getNomeCanal() {
		return nomeCanal;
	}

	public void setNomeCanal(String nomeCanal) {
		this.nomeCanal = nomeCanal;
	}

	public Integer getCodCanal() {
		return codCanal;
	}

	public void setCodCanal(Integer codCanal) {
		this.codCanal = codCanal;
	}
	


	

}
