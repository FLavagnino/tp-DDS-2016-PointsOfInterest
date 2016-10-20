package ar.edu.utn.dds.poi.dto;

import java.util.List;

public class HistoricalSearchResponseDTO {

    private List<HistoricalSearchDTO> historicalSearch;

    public HistoricalSearchResponseDTO(List<HistoricalSearchDTO> historicalSearchDTOs) {
        this.historicalSearch = historicalSearchDTOs;
    }

    public List<HistoricalSearchDTO> getHistoricalSearch() {
        return historicalSearch;
    }

    public void setHistoricalSearch(List<HistoricalSearchDTO> historicalSearch) {
        this.historicalSearch = historicalSearch;
    }
}
