package ar.edu.utn.dds.poi.service.bank;

import java.util.ArrayList;

public class BankServDTO {

	private String banco;
	private Integer x;
	private Integer y;
	private String sucursal;
	private String gerente;
	private ArrayList<String> servicios;
	
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getGerente() {
		return gerente;
	}
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	public ArrayList<String> getServicios() {
		return servicios;
	}
	public void setServicios(ArrayList<String> servicios) {
		this.servicios = servicios;
	}
	
}
