package ar.edu.utn.dds.poi.dto;

import ar.edu.utn.dds.poi.domain.Action;

import java.util.List;

public class ActionsDTO {

    private List<Action> actions;

    public ActionsDTO(List<Action> actions) {
        this.actions = actions;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
