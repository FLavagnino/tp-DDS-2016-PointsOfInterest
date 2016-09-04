package ar.edu.utn.dds.poi.service.historical;

import java.util.ArrayList;
import java.util.List;

public class HistoricalManager {

	private static List<HistoricalSearch> searches = new ArrayList<HistoricalSearch>();
	
	public void saveSearch(HistoricalSearch historicalSearch) {
		searches.add(historicalSearch);
	}
}
