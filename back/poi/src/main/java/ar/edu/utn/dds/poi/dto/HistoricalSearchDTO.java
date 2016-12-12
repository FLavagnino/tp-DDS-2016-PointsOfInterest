package ar.edu.utn.dds.poi.dto;

import java.util.List;

public class HistoricalSearchDTO
{
    private String userName;
    private String filter;
    private Integer resultsNumber;
    private long time;
    private String date;
    private List<String> resultsName;

    public HistoricalSearchDTO(String userName, String filter, Integer resultsNumber, long time, String date, List<String> resultsName)
    {
        this.userName = userName;
        this.filter = filter;
        this.resultsNumber = resultsNumber;
        this.time = time;
        this.date = date;
        this.resultsName = resultsName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFilter() {
        return filter;
    }

    public Integer getResultsNumber() {
        return resultsNumber;
    }

    public long getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public List<String> getResultsName() {
        return resultsName;
    }

    public void setResultsName(List<String> resultsName) {
        this.resultsName = resultsName;
    }
}
