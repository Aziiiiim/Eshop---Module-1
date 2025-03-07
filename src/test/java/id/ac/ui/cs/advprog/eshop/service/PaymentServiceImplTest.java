package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import enums.PaymentMethod;
import enums.PaymentStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private List<Payment> payments;

    @BeforeEach
    void setUp() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("1", PaymentMethod.VOUCHER.getValue(), paymentData, PaymentStatus.PENDING.getValue());
        Payment payment2 = new Payment("2", PaymentMethod.VOUCHER.getValue(), paymentData, PaymentStatus.SUCCESS.getValue());

        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testCreatePayment() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.createPayment(payment);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testUpdateStatus() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        Payment updatedPayment = new Payment(payment.getId(), payment.getMethod(), payment.getPaymentData(), PaymentStatus.SUCCESS.getValue());
        doReturn(updatedPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.updateStatus(payment.getId(), PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testUpdateStatusInvalidPaymentId() {
        doReturn(null).when(paymentRepository).findById("invalid");
        assertThrows(NoSuchElementException.class, () -> paymentService.updateStatus("invalid", PaymentStatus.SUCCESS.getValue()));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testFindByIdIfExists() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindByIdIfNotFound() {
        doReturn(null).when(paymentRepository).findById("invalid");
        assertNull(paymentService.findById("invalid"));
    }

    @Test
    void testFindAllByStatus() {
        doReturn(Collections.singletonList(payments.get(1))).when(paymentRepository).findAllByStatus(PaymentStatus.SUCCESS.getValue());
        List<Payment> results = paymentService.findAllByStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(1, results.size());
        assertEquals(PaymentStatus.SUCCESS.getValue(), results.get(0).getStatus());
    }
}
