package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "123 Main Street");
        paymentData2.put("deliveryFee", "5.00");

        Payment payment1 = new Payment("1", PaymentMethod.VOUCHER.getValue(), paymentData1);
        Payment payment2 = new Payment("2", PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData2);

        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);

        Payment updatedPayment = new Payment(payment.getId(), payment.getMethod(), payment.getPaymentData(), PaymentStatus.SUCCESS.getValue());
        Payment result = paymentRepository.save(updatedPayment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("999");
        assertNull(findResult);
    }

    @Test
    void testFindAllByStatus() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> pendingPayments = paymentRepository.findAllByStatus(PaymentStatus.PENDING.getValue());
        assertEquals(2, pendingPayments.size());
    }
}
