package ar.edu.utn.dds.poi.utils;

import java.util.ArrayList;

public class Formatter {

    public static String keywordToFilter(String keyword) {
        return keyword.substring(1,keyword.length()-1).replace(',',' ');
    }
}
