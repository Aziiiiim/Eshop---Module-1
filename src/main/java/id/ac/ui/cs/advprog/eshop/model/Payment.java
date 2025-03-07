package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class Payment {
	private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.setStatus(status);
    }

    public Payment(String id, String method, Map<String, String> paymentData) {
        this(id, method, paymentData, "PENDING");
    }

    public String getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }

    public Map<String, String> getPaymentData() {
        return paymentData;
    }

    public void setStatus(String status) {
        if (validateStatus(status)) {
        	boolean voucherValid = this.getMethod().equals("Voucher") && 
                    isValidVoucher(this.getPaymentData().get("voucherCode"));
                    
        	boolean cashOnDeliveryValid = this.getMethod().equals("CashOnDelivery") && 
                           isValidCashOnDelivery(this.getPaymentData());

        	if(voucherValid || cashOnDeliveryValid) {
                this.status = status;
        	}
        	else {
        		this.status = "REJECTED";
        	}
        } else {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
    }
    
    private boolean validateStatus(String status) {
        return status.equals("PENDING") || status.equals("SUCCESS") || status.equals("REJECTED") || status.equals("CANCELLED");
    }
    
    private boolean isValidVoucher(String voucherCode) {
        return voucherCode != null &&
               voucherCode.length() == 16 &&
               voucherCode.startsWith("ESHOP") &&
               voucherCode.replaceAll("[^0-9]", "").length() == 8;
    }
    
    private boolean isValidCashOnDelivery(Map<String, String> paymentData) {
        return paymentData.containsKey("address") && paymentData.containsKey("deliveryFee") &&
               !paymentData.get("address").isEmpty() && !paymentData.get("deliveryFee").isEmpty();
    }


}
