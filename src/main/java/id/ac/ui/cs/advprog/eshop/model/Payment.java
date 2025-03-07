package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import enums.PaymentMethod;
import enums.PaymentStatus;

public class Payment {
	private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData, String status) {
        this.id = id;
        this.setPaymentMethod(method);;
        this.paymentData = paymentData;
        this.setStatus(status);
    }

    public Payment(String id, String method, Map<String, String> paymentData) {
        this(id, method, paymentData, PaymentStatus.PENDING.getValue());
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
    
    public void setPaymentMethod(String method) {
    	if(validateMethod(method)) {
    		this.method = method;
    	}
    	else {
            throw new IllegalArgumentException("Invalid payment method: " + status);
        }
    }

    public void setStatus(String status) {
        if (validateStatus(status)) {
        	boolean voucherValid = this.getMethod().equals(PaymentMethod.VOUCHER.getValue()) && 
                    isValidVoucher(this.getPaymentData().get("voucherCode"));
                    
        	boolean cashOnDeliveryValid = this.getMethod().equals(PaymentMethod.CASH_ON_DELIVERY.getValue()) && 
                           isValidCashOnDelivery(this.getPaymentData());

        	if(voucherValid || cashOnDeliveryValid) {
                this.status = status;
        	}
        	else {
        		this.status = PaymentStatus.REJECTED.getValue();
        	}
        } else {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
    }
    
    private boolean validateMethod(String method) {
    	return PaymentMethod.contains(method);
    }
    private boolean validateStatus(String status) {
         return PaymentStatus.contains(status);
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
