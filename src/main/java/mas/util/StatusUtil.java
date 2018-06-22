package mas.util;

import mas.model.constants.Status;

import static mas.model.constants.Status.*;

public class StatusUtil {
    public static Status getStatusById(String id) {
        switch (id) {
            case "1":
                return PENDING;
            case "2":
                return ACCEPTED;
            case "3":
                return REJECTED;
            case "4":
                return DAMAGED;
            case "5":
                return EXPIRED;
            case "6":
                return DEPLETED;
            default:
                return null;
        }
    }
    public static String getStatusId(Status status) {
        switch (status) {
            case PENDING:
                return "1";
            case ACCEPTED:
                return "2";
            case REJECTED:
                return "3";
            case DAMAGED:
                return "4";
            case EXPIRED:
                return "5";
            case DEPLETED:
                return "6";
            default:
                return null;
        }
    }
}
