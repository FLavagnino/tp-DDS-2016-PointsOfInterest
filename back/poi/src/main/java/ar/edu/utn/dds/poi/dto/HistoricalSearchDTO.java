package ar.edu.utn.dds.poi.dto;

import org.joda.time.DateTime;

public class HistoricalSearchDTO {

    private String userName;
    private String filter;
    private Integer resultsNumber;
    private long time;
    private String date;

    public HistoricalSearchDTO(String userName, String filter, Integer resultsNumber, long time, String date)
    {
        this.userName = userName;
        this.filter = filter;
        this.resultsNumber = resultsNumber;
        this.time = time;
        this.date = date;
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
}
