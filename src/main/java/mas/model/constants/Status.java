package mas.model.constants;

public enum Status {
    PENDING,
    ACCEPTED,
    REJECTED,
    DAMAGED,
    EXPIRED,
    DEPLETED;

    public String toString() {
        switch (this) {
            case PENDING:
                return "Oczekująca";
            case ACCEPTED:
                return "Zaakceptowana";
            case REJECTED:
                return "Odrzucona";
            case DAMAGED:
                return "Uszkodzona";
            case EXPIRED:
                return "Przeterminowana";
            case DEPLETED:
                return "Zużyta";
            default:
                return null;
        }
    }
}
