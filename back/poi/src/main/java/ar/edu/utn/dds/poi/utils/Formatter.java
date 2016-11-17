package ar.edu.utn.dds.poi.utils;

import ar.edu.utn.dds.poi.domain.HistoricalSearch;
import ar.edu.utn.dds.poi.dto.HistoricalSearchDTO;
import ar.edu.utn.dds.poi.dto.HistoricalSearchResponseDTO;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Formatter {

    public static String keywordToFilter(String keyword) {
        return keyword.substring(1,keyword.length()-1).replace(',',' ');
    }

    public static DateTime stringToDateTime(String date) {
        if(date.equals("null")) {
            return null;
        }
        String[] splitedDate = date.split("-");
        return new DateTime(
                Integer.valueOf(splitedDate[0]),
                Integer.valueOf(splitedDate[1]),
                Integer.valueOf(splitedDate[2]),
                Integer.valueOf(splitedDate[3]),
                Integer.valueOf(splitedDate[4])
        );
    }

    public static HistoricalSearchResponseDTO historicalSearchToDTO(List<HistoricalSearch> historicalSearches) {
        List<HistoricalSearchDTO> historicalSearchDTOs = new ArrayList<>();
        for (HistoricalSearch historicalSearch : historicalSearches) {
            historicalSearchDTOs.add(new HistoricalSearchDTO(
                    historicalSearch.getUserName(),
                    historicalSearch.getFilter(),
                    historicalSearch.getResultsNumber(),
                    historicalSearch.getTime(),
                    historicalSearch.getDate().toString()
            ));
        }
        return new HistoricalSearchResponseDTO(historicalSearchDTOs);
    }
}
