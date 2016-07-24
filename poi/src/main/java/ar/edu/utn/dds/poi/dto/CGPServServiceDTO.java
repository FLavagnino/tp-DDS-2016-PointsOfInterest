package ar.edu.utn.dds.poi.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class CGPServServiceDTO {
	
	private String nombre;
	private ArrayList<HashMap<String, Integer>> horarios;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<HashMap<String, Integer>> getHorarios() {
		return horarios;
	}
	public void setHorarios(ArrayList<HashMap<String, Integer>> horarios) {
		this.horarios = horarios;
	}
	
	

}
