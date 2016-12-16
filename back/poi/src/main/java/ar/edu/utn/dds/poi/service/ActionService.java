package ar.edu.utn.dds.poi.service;

import ar.edu.utn.dds.poi.domain.Action;
import ar.edu.utn.dds.poi.domain.User;
import ar.edu.utn.dds.poi.repository.ActionRepository;
import ar.edu.utn.dds.poi.repository.UserRepository;
import ar.edu.utn.dds.poi.utils.Formatter;

import java.util.List;

public class ActionService {

    private UserRepository userRepository;

    public ActionService() {
        userRepository =new UserRepository();
    }

    public User updateActionsOfUser(User user, List<Action> actions) {
        user.setActions(actions);
        userRepository.delete(user);
        userRepository.save(user);
        return user;
    }

    public User updateActionsOfUser(User user, String actions) {
        return updateActionsOfUser(user, Formatter.toActions(actions, user));
    }
}
