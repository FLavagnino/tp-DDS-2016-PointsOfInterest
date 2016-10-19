package ar.edu.utn.dds.poi.dto;

import ar.edu.utn.dds.poi.service.historical.SearchResult;

/**
 * Created by facundolavagnino on 18/10/16.
 */
public class PoiResultDTO {

    private SearchResult searchResult;
    private int searchKey;

    public PoiResultDTO(SearchResult searchResult, int searchKey) {
        this.searchResult = searchResult;
        this.searchKey = searchKey;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public int getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(int searchKey) {
        this.searchKey = searchKey;
    }
}
