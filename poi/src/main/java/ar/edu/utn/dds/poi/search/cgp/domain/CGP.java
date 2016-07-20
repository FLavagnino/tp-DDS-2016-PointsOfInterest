package ar.edu.utn.dds.poi.search.cgp.domain;

import java.util.ArrayList;

public class CGP {

	private Integer comuna;
	private String zonas;
	private String director;
	private String domicilio;
	private String telefono;
	private ArrayList<ServiceDTO> servicios;
	
	public Integer getComuna() {
		return comuna;
	}
	public void setComuna(Integer comuna) {
		this.comuna = comuna;
	}
	public String getZonas() {
		return zonas;
	}
	public void setZonas(String zonas) {
		this.zonas = zonas;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public ArrayList<ServiceDTO> getServicios() {
		return servicios;
	}
	public void setServicios(ArrayList<ServiceDTO> servicios) {
		this.servicios = servicios;
	}
		
}