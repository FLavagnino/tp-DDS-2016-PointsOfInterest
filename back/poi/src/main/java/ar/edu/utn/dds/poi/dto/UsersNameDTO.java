package ar.edu.utn.dds.poi.dto;

import java.util.List;

public class UsersNameDTO {

    private List<String> names;

    public UsersNameDTO(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
