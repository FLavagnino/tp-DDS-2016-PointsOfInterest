package ar.edu.utn.dds.poi.utils;

import ar.edu.utn.dds.poi.domain.Action;
import ar.edu.utn.dds.poi.domain.Log;
import ar.edu.utn.dds.poi.domain.LogResult;
import ar.edu.utn.dds.poi.domain.User;
import ar.edu.utn.dds.poi.dto.ActionsDTO;
import ar.edu.utn.dds.poi.dto.HistoricalSearchDTO;
import ar.edu.utn.dds.poi.dto.HistoricalSearchResponseDTO;

import ar.edu.utn.dds.poi.dto.UsersNameDTO;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Formatter {

    public static String keywordToFilter(String keyword) 
    {
        return keyword.substring(1,keyword.length()-1).replace(',',' ');
    }

    public static DateTime stringToDateTime(String date) 
    {
        if(date.equals("null")) 
        {
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

    public static HistoricalSearchDTO logDTO(Log historicalSearch) 
    {
        return new HistoricalSearchDTO(
                    historicalSearch.getUserName(),
                    historicalSearch.getFilter(),
                    historicalSearch.getResultsNumber(),
                    historicalSearch.getTime(),
                    historicalSearch.getDate().toString(),
                    historicalSearch.getResults() == null ? null : historicalSearch.getResults().stream().map(LogResult::getPoiName).collect(Collectors.toCollection(ArrayList::new))
        );
    }

    public static UsersNameDTO usersNameDto(List<User> users) {
        return new UsersNameDTO(users.stream().map(User::getUserName).collect(Collectors.toCollection(ArrayList::new)));
    }

    public static ActionsDTO userActionsDto(User user) {
        return new ActionsDTO(user.getActions());
    }
}
