package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        if (paymentRepository.findById(payment.getId()) == null) {
            paymentRepository.save(payment);
            return payment;
        }
        return null;
    }

    public Payment updateStatus(String paymentId, String status) {
        Payment payment = paymentRepository.findById(paymentId);
        if (payment != null) {
            Payment updatedPayment = new Payment(payment.getId(), payment.getMethod(), payment.getPaymentData(), status);
            paymentRepository.save(updatedPayment);
            return updatedPayment;
        } else {
            throw new NoSuchElementException("Payment not found");
        }
    }

    public Payment findById(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public List<Payment> findAllByStatus(String status) {
        return paymentRepository.findAllByStatus(status);
    }
}
