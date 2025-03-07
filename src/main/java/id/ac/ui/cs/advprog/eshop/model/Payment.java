package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData, String status) {
    }
    
    public Payment(String id, String method, Map<String, String> paymentData) {
    }

    public String getId() {
        return null;
    }

    public String getMethod() {
        return null;
    }

    public String getStatus() {
        return null;
    }

    public Map<String, String> getPaymentData() {
        return null;
    }

    public void setStatus(String status) {
    }
}
