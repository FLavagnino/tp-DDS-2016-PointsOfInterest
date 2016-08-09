package ar.edu.utn.dds.poi.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceDTO 
{
	private String nombre;
	private ArrayList<HashMap<String, Integer>> horarios;
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public ArrayList<HashMap<String, Integer>> getHorarios() 
	{
		return horarios;
	}
	
	public void setHorarios(ArrayList<HashMap<String, Integer>> horarios) 
	{
		this.horarios = horarios;
	}
	
	public Integer getDiaSemana(HashMap<String, Integer> hashMap) 
	{
		return hashMap.get("diaSemana");
	}
	
	public Integer getHoraDesde(HashMap<String, Integer> hashMap) 
	{
		return hashMap.get("horaDesde");
	}
	
	public Integer getMinutosDesde(HashMap<String, Integer> hashMap) 
	{
		return hashMap.get("minutosDesde");
	}
	
	public Integer getHoraHasta(HashMap<String, Integer> hashMap) 
	{
		return hashMap.get("horaHasta");
	}
	
	public Integer getMinutosHasta(HashMap<String, Integer> hashMap) 
	{
		return hashMap.get("minutosHasta");
	}
}
