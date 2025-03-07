package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return null;
    }

    public Payment updateStatus(String paymentId, String status) {
        return null;
    }

    public Payment findById(String paymentId) {
        return null;
    }

    public List<Payment> findAllByStatus(String status) {
    	return null;
    }
}
