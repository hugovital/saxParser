package com.testes;

public class DadosControle {
	
	public AreaCanal getAreaCanal() {
		return areaCanal;
	}
	public void setAreaCanal(AreaCanal areaCanal) {
		this.areaCanal = areaCanal;
	}
	private String firstName;
	private String lastName;
	private String nickName;
	private String salary;
	
	@MyAnnotation(nome="AREA-CANA", classRetorno=AreaCanal.class)
	private AreaCanal areaCanal;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	


}
