package ar.edu.utn.dds.poi.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readTextFile {
	
	public static void readText(String file) throws FileNotFoundException, IOException {
		
		String characters;
		String nameShop;
		ArrayList<String> things;
		
		FileReader fr = new FileReader(file);
		BufferedReader buffer = new BufferedReader(fr);
			while((characters = buffer.readLine())!= null){
				while((characters = buffer.readLine()) != ";"){
					nameShop = characters;
				}
				while((characters = buffer.readLine()) != " "){
					//things.add(characters);
				}
			}
		buffer.close();
	}
	
}
