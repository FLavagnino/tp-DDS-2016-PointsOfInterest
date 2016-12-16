package ar.edu.utn.dds.poi.service;

import ar.edu.utn.dds.poi.domain.Action;
import ar.edu.utn.dds.poi.domain.User;
import ar.edu.utn.dds.poi.repository.ActionRepository;

import java.io.Serializable;
import java.util.List;

public class ActionService {

    private ActionRepository actionRepository;

    public ActionService() {
        actionRepository = new ActionRepository();
    }

    public List<Serializable> updateActionsToUser(User user, List<Action> actions) {
        List<Action> a = actionRepository.getActionsByUserID(user);
        actionRepository.deleteAll(a);
        return actionRepository.saveAll(actions);
    }
}
