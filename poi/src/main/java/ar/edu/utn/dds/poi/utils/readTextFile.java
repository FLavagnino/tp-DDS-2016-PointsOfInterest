package ar.edu.utn.dds.poi.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readTextFile {
	
	protected String characters;
	protected String nameShop = "";
	protected String onlyThings = "";
	
	public void readText(String file) throws FileNotFoundException, IOException {
		
		FileReader fr = new FileReader(file);
		BufferedReader buffer = new BufferedReader(fr);
			while((characters = buffer.readLine())!= null){
				while((characters = buffer.readLine()) != ";"){					
						nameShop.concat(characters);	
				}
					onlyThings.concat(characters);
			}
		buffer.close();
	}
	public String getNameShop(){
		return this.nameShop;
	}
	public String getOnlyThings(){
		return this.onlyThings;
	}
}
