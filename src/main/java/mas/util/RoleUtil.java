package mas.util;

import mas.model.constants.Role;

import java.util.ArrayList;
import java.util.List;

import static mas.model.constants.Role.*;

public class RoleUtil {
    public static List<Role> getRolesByIds(List<String> ids) {
        List<Role> roles = new ArrayList<>();
        for(String id : ids) {
            switch (id) {
                case "1":
                    roles.add(CONTROLLER);
                    break;
                case "2":
                    roles.add(DESIGNER);
                    break;
                case "3":
                    roles.add(TRADER);
                    break;
            }
        }
        return roles;
    }
    public static List<String> getRoleIds(List<Role> roles) {
        List<String> ids = new ArrayList<>();
        for(Role role : roles) {
            switch (role) {
                case CONTROLLER:
                    ids.add("1");
                    break;
                case DESIGNER:
                    ids.add("2");
                    break;
                case TRADER:
                    ids.add("3");
                    break;
            }
        }
        return ids;
    }
}
