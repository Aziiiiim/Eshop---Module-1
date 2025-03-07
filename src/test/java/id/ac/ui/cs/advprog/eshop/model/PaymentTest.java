package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentTest {

    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("1", "Voucher", paymentData, "PENDING");
        assertEquals("1", payment.getId());
        assertEquals("Voucher", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("1", "Voucher", paymentData, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1", "Voucher", paymentData, "INVALID");
        });
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("1", "Voucher", paymentData, "PENDING");
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("1", "Voucher", paymentData, "PENDING");
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("INVALID"));
    }

    @Test
    void testCreateCashOnDeliveryPaymentSuccess() {
        paymentData.clear();
        paymentData.put("address", "123 Main Street");
        paymentData.put("deliveryFee", "5.00");
        
        Payment payment = new Payment("2", "CashOnDelivery", paymentData, "PENDING");
        assertEquals("2", payment.getId());
        assertEquals("CashOnDelivery", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertEquals("123 Main Street", payment.getPaymentData().get("address"));
        assertEquals("5.00", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    void testCreateCashOnDeliveryPaymentRejected() {
        paymentData.clear();
        paymentData.put("address", ""); // Empty address
        paymentData.put("deliveryFee", "5.00");
        
        Payment payment = new Payment("3", "CashOnDelivery", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
    
    @Test
    void testCreatePaymentWithInvalidVoucher() {
        paymentData.put("voucherCode", "INVALID123456789");
        Payment payment = new Payment("4", "Voucher", paymentData, "PENDING");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateCashOnDeliveryPaymentNoAddress() {
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "10.00");
        Payment payment = new Payment("5", "CashOnDelivery", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateCashOnDeliveryPaymentNoDeliveryFee() {
        paymentData.put("address", "456 Elm Street");
        paymentData.put("deliveryFee", "");
        Payment payment = new Payment("6", "CashOnDelivery", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithUnknownMethod() {
        Payment payment = new Payment("7", "Crypto", paymentData, "PENDING");
        assertEquals("REJECTED", payment.getStatus()); 
    }
}
