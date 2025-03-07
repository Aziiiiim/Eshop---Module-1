package enums;

public enum PaymentMethod {
    VOUCHER("Voucher"),
    CASH_ON_DELIVERY("CashOnDelivery");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contains(String method) {
        for (PaymentMethod pm : PaymentMethod.values()) {
            if (pm.getValue().equals(method)) {
                return true;
            }
        }
        return false;
    }
}
