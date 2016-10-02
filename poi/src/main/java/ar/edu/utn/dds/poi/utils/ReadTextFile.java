package ar.edu.utn.dds.poi.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.dds.poi.domain.Shop;

public class ReadTextFile 
{	
	public List<Shop> shopsToUpdate;
	
	public List<Shop> readText(String file) throws FileNotFoundException, IOException 
	{	
		shopsToUpdate = new ArrayList<Shop>();
		
		FileReader fr = new FileReader(file);
		BufferedReader buffer = new BufferedReader(fr);
		
		String line;
		
		while((line = buffer.readLine())!= null)
		{
			String[] parts = line.split(";");
						
			Shop shopToUpdate = new Shop(parts[0], parts[1]);
			shopsToUpdate.add(shopToUpdate);
		}
		
		buffer.close();
		return shopsToUpdate;
	}
}
