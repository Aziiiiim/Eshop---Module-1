package enums;

public enum PaymentStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED"),
    CANCELLED("CANCELLED");

    private final String value;

    private PaymentStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.name().equals(param)) {
                return true;
            }
        }
        return false;
    }

    public String getValue() {
        return value;
    }
}
