package br.wals.hrpayroll.services;

import br.wals.hrpayroll.entities.Payment;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment getPayment(long workerId, int days) {
        return new Payment("Bob", 200.00, days);
    }
}
