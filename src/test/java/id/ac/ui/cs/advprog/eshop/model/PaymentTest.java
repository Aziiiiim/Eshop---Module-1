package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.PaymentMethod;
import enums.PaymentStatus;

class PaymentTest {

    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), paymentData, PaymentStatus.PENDING.getValue());
        assertEquals("1", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), paymentData, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1", PaymentMethod.VOUCHER.getValue(), paymentData, "INVALID");
        });
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), paymentData, PaymentStatus.PENDING.getValue());
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), paymentData, PaymentStatus.PENDING.getValue());
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("INVALID"));
    }

    @Test
    void testCreateCashOnDeliveryPaymentSuccess() {
        paymentData.clear();
        paymentData.put("address", "123 Main Street");
        paymentData.put("deliveryFee", "5.00");
        
        Payment payment = new Payment("2", PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData, PaymentStatus.PENDING.getValue());
        assertEquals("2", payment.getId());
        assertEquals(PaymentMethod.CASH_ON_DELIVERY.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertEquals("123 Main Street", payment.getPaymentData().get("address"));
        assertEquals("5.00", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    void testCreateCashOnDeliveryPaymentRejected() {
        paymentData.clear();
        paymentData.put("address", ""); // Empty address
        paymentData.put("deliveryFee", "5.00");
        
        Payment payment = new Payment("3", PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
    
    @Test
    void testCreatePaymentWithInvalidVoucher() {
        paymentData.put("voucherCode", "INVALID123456789");
        Payment payment = new Payment("4", PaymentMethod.VOUCHER.getValue(), paymentData, PaymentStatus.PENDING.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateCashOnDeliveryPaymentNoAddress() {
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "10.00");
        Payment payment = new Payment("5", PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateCashOnDeliveryPaymentNoDeliveryFee() {
        paymentData.put("address", "456 Elm Street");
        paymentData.put("deliveryFee", "");
        Payment payment = new Payment("6", PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentWithUnknownMethod() {
    	assertThrows(IllegalArgumentException.class, () -> {
    		Payment payment = new Payment("7", "Crypto", paymentData, PaymentStatus.PENDING.getValue());
    	});
 
    }
}
